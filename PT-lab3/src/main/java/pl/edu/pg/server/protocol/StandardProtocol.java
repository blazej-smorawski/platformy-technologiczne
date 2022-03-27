package pl.edu.pg.server.protocol;

public class StandardProtocol implements Protocol{
    private int state = 0;

    @Override
    public Object getResponseTo(Object object) {
        if(state==0 && object.getClass() == String.class &&
                ((String)object).equals("Hello")) {
            state+=1;
            return "Session Started";
        }

        if(state==1 && object.getClass() == String.class &&
                ((String)object).equals("Bye")) {
            return "Session Ended";
        }
        return "Something went wrong!";
    }
}
