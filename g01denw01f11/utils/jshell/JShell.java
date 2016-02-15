package com.g01denw01f11.utils.jshell;

/**
 * @author G01denW01f11
 *
 * Custom shell, with (hopefully (at some point)) 'smart' auto-complete
 */
public class JShell {

    public JShell() {}

    private void run() {
        boolean shouldExit = false;
        String input;
        while (!shouldExit) {
            input = System.console().readLine();
        }
    }
    public static void main(String[] args) {
        JShell shell = new JShell();
        shell.run();
    }
}
