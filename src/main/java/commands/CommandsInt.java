package commands;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface CommandsInt {
    void command(String name) throws IOException, ParseException;
}

