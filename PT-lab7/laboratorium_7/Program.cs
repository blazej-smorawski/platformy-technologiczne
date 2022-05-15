using System; 
using System.Collections.Generic;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;

namespace laboratorium_7
{
    static class Program
    {
        static void Main(string[] args)
        {
            string path = args[0];
            if (Wypisz(path))
            {
                Console.WriteLine();
                Console.WriteLine("Najstarszy plik: {0}", (new DirectoryInfo(path)).NajstarszyPlik());
                CreateCollection(path);
            }
        }

        public static bool Wypisz(string path)
        {
            Console.WriteLine(path);
            if (File.Exists(path))  
            {
                WypiszPlik(path, 0);
            }
            else if (Directory.Exists(path))
            {
                WypiszKatalog(path, 0);
            }
            else
            {
                Console.WriteLine("Błędny katalog!");
                return false;
            }
            return true;
        }

        public static void WypiszKatalog(string path, int d)
        {
            string[] pliki = Directory.GetFiles(path);
            foreach (var plik in pliki)
            {
                WypiszPlik(plik, d);
            }

            string[] katalogi = Directory.GetDirectories(path);
            foreach (var katalog in katalogi)
            {
                string[] tokeny = katalog.Split("\\");
                string nazwaKatalogu = tokeny[^1];
                for (int i = 0; i < d; i++)
                {
                    Console.Write("    ");
                }
                DirectoryInfo dInfo = new DirectoryInfo(katalog);
                DateTime najstarszy = dInfo.NajstarszyPlik();
                int liczbaPlikow = (dInfo.GetDirectories().Length + dInfo.GetFiles().Length);
                Console.Write("{0} ({1}) {2} {3}", nazwaKatalogu, liczbaPlikow, najstarszy, dInfo.GetRAHS());
                Console.WriteLine();
                WypiszKatalog(katalog, d + 1);
            }
        }

        public static void WypiszPlik(string path, int depth)
        {
            string[] tokeny = path.Split("\\");
            string nazwaPliku = tokeny[^1];
            for (int i = 0; i < depth; i++)
            {
                Console.Write("    ");
            }
            FileInfo fileInfo = new FileInfo(path);
            Console.Write("{0} ({1} bytes) {2}", nazwaPliku, fileInfo.Length, fileInfo.GetRAHS());
            Console.WriteLine();
        }

        public static DateTime NajstarszyPlik(this DirectoryInfo katalog)
        {
            DateTime najstarszy = new DateTime(2200, 12, 31);
            
            foreach (var dInfo in katalog.GetDirectories())
            {
                DateTime najstarszyWKatalogu = dInfo.NajstarszyPlik();
                if (najstarszyWKatalogu < najstarszy)
                {
                    najstarszy = najstarszyWKatalogu;
                }
            }
            foreach (var plik in katalog.GetFiles())
            {
                DateTime data = plik.CreationTime;
                if (data < najstarszy)
                {
                    najstarszy = data;
                }
            }
            return najstarszy;
        }

        public static string GetRAHS(this FileSystemInfo fsinfo)
        {
            string wyjscie = "";

            FileAttributes fileAttributes = fsinfo.Attributes;

            if ((fileAttributes & FileAttributes.ReadOnly) == FileAttributes.ReadOnly)
            {
                wyjscie += 'r';
            }
            else
            {
                wyjscie += '-';
            }

            if ((fileAttributes & FileAttributes.Archive) == FileAttributes.Archive)
            {
                wyjscie += 'a';
            }
            else
            {
                wyjscie += '-';
            }

            if ((fileAttributes & FileAttributes.Hidden) == FileAttributes.Hidden)
            {
                wyjscie += 'h';
            }
            else
            {
                wyjscie += '-';
            }

            if ((fileAttributes & FileAttributes.System) == FileAttributes.System)
            {
                wyjscie += 's';
            }
            else
            {
                wyjscie += '-';
            }
            return wyjscie;
        }


        public static void CreateCollection(string path)
        {
            SortedDictionary<string, int> collection = new SortedDictionary<string, int>(new StringComparer());
            if (File.Exists(path))
            {
                FileInfo file = new FileInfo(path);
                collection.Add(file.Name, (int)file.Length);
            }
            else if (Directory.Exists(path))
            {
                DirectoryInfo dir = new DirectoryInfo(path);
                foreach (var subdir in dir.GetDirectories())
                {
                    collection.Add(subdir.Name, (subdir.GetFiles().Length + subdir.GetDirectories().Length));
                }
                foreach (var file in dir.GetFiles())
                {
                    collection.Add(file.Name, (int)file.Length);
                }
            }
            FileStream fs = new FileStream("DataFile.dat", FileMode.Create);
            BinaryFormatter formatter = new BinaryFormatter();
            try
            {   
                formatter.Serialize(fs, collection);
            }
            catch (SerializationException e)
            {
                Console.WriteLine("Serialization error: {0}", e.Message);
            }
            fs.Close();
            Deserialize();
        }

        public static void Deserialize()
        {
            SortedDictionary<string, int> collection = new SortedDictionary<string, int>(new StringComparer());
            FileStream fs = new FileStream("DataFile.dat", FileMode.Open);
            try
            {
                BinaryFormatter formatter = new BinaryFormatter();
                collection = (SortedDictionary<string, int>)formatter.Deserialize(fs);
            }
            catch (SerializationException e)
            {
                Console.WriteLine("Serialization error: {0}", e.Message);
            }

            foreach(var file in collection)
            {
                Console.WriteLine("{0} -> {1}", file.Key, file.Value);
            }
        }
    }

    [Serializable]
    public class StringComparer : IComparer<string>
    {
        public int Compare(string a, string b)
        {
            if (a.Length > b.Length)
            {
                return 1;
            }
            else if (a.Length < b.Length)
            {
                return -1;
            }
            else
            {
                return a.CompareTo(b);
            }
        }
    }


}
