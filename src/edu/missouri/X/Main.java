/*
 * The MIT License
 *
 * Copyright 2014 Ryan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package edu.missouri.X;

import edu.missouri.commands.InitializeProject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 *
 * @author Ryan
 */
public class Main {



    public Main(String[] args) {

        if (args.length > 0) {
            String command = args[0];
            switch (command) {
                case "init":
//                    InitializeProject ip = new InitializeProject(new String[] {} );
//                    ip.x(new String[] {});
                    File f = new File("x.java");
                    if(f.exists()) {
                        f.delete();
                      
                    }
                    try {
                        PrintWriter writer = new PrintWriter(f);
                        writer.print(Templates.PROJECT_FILE);
                        writer.close();

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    break;

                case "add": //act as generator, could add dependency, could add controller, model, etc...
                case "compile": //compile project
                case "build": //build project?
                case "test": //run tests
                case "package": //build, run tests, make jar
                case "publish": //package and upload somewhere
                case "lookup": //search for dependency
                case "works?": //run all tests
                case "go!": //run works, run package
                case "clean":
                    break;
            }
        } else {
            //find project.java
            new DefaultCommandRunner().run();
        }
    }

    public void cleanup(File classFile) {
        System.out.println("CLEANING UP CLASSFILE: " + classFile.getAbsolutePath());

        for (String file : classFile.list()) {

            File f = new File(classFile.getAbsoluteFile() + "/" + file);
            System.out.println("DELETING FILE: " + f.getAbsolutePath());
            f.delete();
        }

        System.out.println(Arrays.toString(classFile.list()));

        System.out.println("DELETING DIRECTORY: " + classFile);
        classFile.delete();
    }

    public static void main(String[] args) {
        new Main(args);
    }

    public void log(String message) {
        System.out.println(message);
    }
}
