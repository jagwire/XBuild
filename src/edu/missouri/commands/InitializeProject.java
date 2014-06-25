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
package edu.missouri.commands;

import edu.missouri.X.annotations.Command;
import edu.missouri.X.annotations.DoublePrompt;
import edu.missouri.X.annotations.StringPrompt;
import edu.missouri.X.annotations.YesNoPrompt;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
@Command("init")
public class InitializeProject {

//    @Prompt(id="num",
//            text="what do you wnat?",
//            return_type=Integer.class)
//    private int num;
//    
//    @Prompt("Number")
//    @PromptValue(Integer.class)
//    @PromptText("What number is your favorite?")
//    private int val;
    
    @StringPrompt({"name", "What is the name of your project?"})
    private String name;

    @DoublePrompt({"version", "What version would you like to start at?"})
    private double version;

    @StringPrompt({"convention", "What project pattern would you like to create?"})
    private String convention;

    @YesNoPrompt({"junit", "Would you like to include JUnit?"})
    private boolean junit;

    @YesNoPrompt({"mockito", "Would you like to include Mockito?"})
    private boolean mockito;

    @YesNoPrompt({"git", "Would you like to initialize a git repository?"})
    private boolean git;

    @YesNoPrompt({"github", "Would you like to initialize a github project?"})
    private boolean github;

    @YesNoPrompt({"watch", "Would you like to watch the project for changes?"})
    private boolean watch;

    private boolean thereAreNoArguments = true;

    public InitializeProject(String[] args) {
        if (args.length > 1) {
            thereAreNoArguments = false;
        }
        
        Field[] fields = getClass().getDeclaredFields();
        for(Field field: fields) {
//            System.out.println("PROCESSING FIELD: "+field.getName());

            Annotation[] annotations = field.getDeclaredAnnotations();
//            Annotation[] annotations = getClass().getDeclaredAnnotations();
            for(Annotation annotation: annotations) {
                
//                System.out.println("PROCESSING ANNOTATION: "+annotation.annotationType().getName()+" FOR FIELD: "+field.getName());
                String name = "";
                if(annotation.annotationType().getName().contains("StringPrompt")) {
                    name = ((StringPrompt)annotation).value()[0];
//                    prompts.put(name, annotation);
                } else if(annotation.annotationType().getName().contains("DoublePrompt")) {
                    name = ((DoublePrompt)annotation).value()[0];
//                    prompts.put(name, annotation);
                } else if(annotation.annotationType().getName().contains("YesNoPrompt")) {
                    name = ((YesNoPrompt)annotation).value()[0];
                }
                
                prompts.put(name, annotation);
                promptOutputContainers.put(name, field);
            }
        }
    }


    public void x(String[] args) {
        if (thereAreNoArguments) {
            //prompt for project name
            prompt("name");
            //prompt for starting project version
            prompt("version");
            //prompt for project pattern (MVC, etc...)
            prompt("convention");

            //prompt for JUnit
            prompt("junit");
            //prompt for Mockito
            prompt("mockito");
            //prompt for GitHub
            prompt("git");
            prompt("github");
            //prompt for Git watch
            prompt("watch");

            handleResults();

        } else {
            //parse arguments ala:
            // x init MyProject 1.0 MVC ALL
        }
    }

    // prompt_name ->Prompt type
    Map<String, Annotation> prompts = new HashMap<>();
    
    // prompt_name -> field to populate
    Map<String, Field> promptOutputContainers = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    private void prompt(String promptName) {
        
        Annotation a = prompts.get(promptName);
        Field f = promptOutputContainers.get(promptName);

        try {
            if (a.annotationType().equals(StringPrompt.class)) {
                System.out.println(((StringPrompt) a).value()[1]);
                String input = scanner.nextLine();

                String value = input;
                
                getClass().getDeclaredField(f.getName()).set(this, value);

            } else if (a.annotationType().equals(DoublePrompt.class)) {
                System.out.println(((DoublePrompt) a).value()[1]);
                String input = scanner.nextLine();

                double value = Double.valueOf(input);
                getClass().getDeclaredField(f.getName()).setDouble(this, value);
            } else if (a.annotationType().equals(YesNoPrompt.class)) {
                System.out.println(((YesNoPrompt) a).value()[1]);
                String input = scanner.nextLine();

                boolean value = input.toUpperCase().equals("YES");
                getClass().getDeclaredField(f.getName()).setBoolean(this, value);
            }
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(InitializeProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(InitializeProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(InitializeProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InitializeProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleResults() {
        System.out.println("PROJECT NAME: "+name);
    }

}
