package thread.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hguo on 4/23/16.
 */
public class RunTime {
    /**
     * executeCommand
     *
     * @param commands
     * @throws IOException
     */
    public static void executeCommand(String... commands) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process;
        process = runtime.exec(commands);
        BufferedReader br = new BufferedReader(new InputStreamReader(process
                .getInputStream()));
        String inline;
        while ((inline = br.readLine()) != null) {
            System.out.println(inline);
        }
        br.close();
    }


    public static void main(String[] args) {
        try {
            RunTime.executeCommand("/bin/sh", "-c", "ps -ewf | grep google | awk '{print $0}'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
