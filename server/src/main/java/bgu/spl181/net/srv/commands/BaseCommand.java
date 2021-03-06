package bgu.spl181.net.srv.commands;

import java.io.Serializable;

public interface BaseCommand<T> extends Serializable {

    Serializable execute(T arg);
}
