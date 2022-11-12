package commands;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface commandsInt {
    void command(String name) throws IOException, ParseException;
}

