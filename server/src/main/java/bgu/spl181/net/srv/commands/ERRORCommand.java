package bgu.spl181.net.srv.commands;


import java.io.Serializable;

public class ERRORCommand<String> implements BaseCommand<String> {
    @Override
    public Serializable execute(String arg) {
        return null;
    }
}
