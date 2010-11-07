/*
 *  The MIT License
 * 
 *  Copyright 2010 Georgios Migdos <cyberpython@gmail.com>.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package glossa.ui;

import glossa.interpreter.Interpreter;
import glossa.interpreter.InterpreterListener;
import glossa.interpreter.symboltable.SymbolTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 *
 * @author Georgios Migdos <cyberpython@gmail.com>
 */
public class CLI implements InterpreterListener {

    private boolean stepByStep;

    public CLI(boolean executeStepByStep) {
        this.stepByStep = executeStepByStep;
    }

    public void commandExecuted(Interpreter sender, boolean wasPrintStatement) {
        if (!stepByStep) {
            sender.resume();
        } else {
            if (!wasPrintStatement) {
                checkUserInputAfterStep(sender);
            } else {
                sender.resume();
            }
        }

    }

    public void stackPopped() {
    }

    public void stackPushed(SymbolTable newSymbolTable) {
    }

    private void checkUserInputAfterStep(Interpreter sender) {
        System.out.print("Continue? (Enter=Yes / Anything+Enter=No)");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = r.readLine();
            if (s.equals("")) {
                sender.resume();
            } else {
                sender.stop();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void execute(File sourceCodeFile, PrintStream out, PrintStream err, InputStream in) {
        Interpreter inter = new Interpreter(sourceCodeFile, out, err, in);
        inter.addListener(this);
        Thread t = new Thread(inter);
        t.start();
    }

    public void execute(File sourceCodeFile, File inputFile) {
        try {
            this.execute(sourceCodeFile, System.out, System.err, new FileInputStream(inputFile));
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getLocalizedMessage());
        }
    }

    public void execute(File sourceCodeFile) {
        this.execute(sourceCodeFile, System.out, System.err, System.in);
    }
}
