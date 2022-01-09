package com.oneencrypt.oneencrypt.InputlogicTest;

import com.oneencrypt.oneencrypt.central.inputlogic.ConsoleInput;
import com.oneencrypt.oneencrypt.central.inputlogic.FileInput;
import com.oneencrypt.oneencrypt.central.inputlogic.Input;
import com.oneencrypt.oneencrypt.central.inputlogic.InputFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InputFactoryTest {
    InputFactory inputFactory;
    String ConsoleInputString = "ConsoleInput";
    String FileInputString = "FileInput";
    @BeforeEach
    public  void setup(){
        this.inputFactory = new InputFactory();
    }
    @Test
    public void checkInputFactoryReturnsNull(){
        Input input = inputFactory.getInputObject("null");
        Assertions.assertNull(input);
    }
    @Test
    public void checkInputFactoryReturnsConsoleInput(){
        Input input = inputFactory.getInputObject(ConsoleInputString);
        Assertions.assertSame(input.getClass(), ConsoleInput.class);
    }
    @Test
    public void checkInputFactoryReturnsFileInput(){
        Input input = inputFactory.getInputObject(FileInputString);
        Assertions.assertSame(input.getClass(), FileInput.class);
    }
}
