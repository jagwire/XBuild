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

import edu.missouri.X.utilities.XCompiler;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 *
 * @author Ryan
 */
public class DefaultCommandRunner {

    private static final String FILENAME = "x.java";

    public void run() {
        log("Welcome To X!");
        String filename = System.getProperty("user.dir") + File.separator + FILENAME;
        File sourceFile = new File(filename);
        String classname = System.getProperty("user.dir");
        File classFile = new File(classname + "/tmp");
        classFile.mkdirs();
        if (sourceFile.exists()) {
            try {
                log("SUCCESS!");
                if (classFile.exists()) {
                    log("CLASS EXISTS!");
                }

                XCompiler compiler = new XCompiler();
                compiler.setOutputDirectory(classFile.getAbsolutePath());
                compiler.addSourceFile(sourceFile);
                compiler.compile();

                compiler.cleanup();

                loadProjectClass(classFile);

            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            classFile.delete();
            log("NO PROJECT FILE FOUND:" + sourceFile.getAbsolutePath());
            log("Usage: x <command> (args)");
        }
    }

    private void loadProjectClass(File classFile) {
        //                System.out.println("file:/"+System.getProperty("user.dir")+File.separator+"project.class");
        try {
            URLClassLoader cl = new URLClassLoader(new URL[]{classFile.toURI().toURL()}, this.getClass().getClassLoader());

            Class clazz = cl.loadClass("x");

            Base b = (Base) clazz.newInstance();
            b.build();

            cleanup(classFile);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void log(String message) {
        System.out.println(message);
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
}
