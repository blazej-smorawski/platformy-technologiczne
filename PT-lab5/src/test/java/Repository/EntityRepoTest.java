package Repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.edu.pg.repository.EntityRepo;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

public class EntityRepoTest {
    @Test
    public void entityRepo_removeNotExisting() {
        EntityRepo<String, String> repo = new EntityRepo<String, String>();
        repo.save("abc","xyz");

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            repo.delete("xyz");
        });
    }

    @Test
    public void entityRepo_findNotExisting() {
        EntityRepo<String, String> repo = new EntityRepo<String, String>();
        repo.save("abc","xyz");

        Optional<String> entity = repo.find("xyz");

        assertThat(entity.isPresent()).isEqualTo(false);
    }

    @Test
    public void entityRepo_findExisting() {
        EntityRepo<String, String> repo = new EntityRepo<String, String>();
        repo.save("abc","xyz");

        Optional<String> entity = repo.find("abc");

        assertThat(entity.get()).isNotEqualTo(null);
    }

    @Test
    public void entityRepo_saveOverExisting() {
        EntityRepo<String, String> repo = new EntityRepo<String, String>();
        repo.save("abc","xyz");

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            repo.save("abc","def");
        });
    }
}
