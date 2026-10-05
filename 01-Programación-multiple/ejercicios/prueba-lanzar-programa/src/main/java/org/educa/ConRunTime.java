package org.educa;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ConRunTime {
    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime r = Runtime.getRuntime();
        Process p = r.exec("mate-terminal");
        p.waitFor(10, TimeUnit.SECONDS);
        p.destroy();
    }
}
