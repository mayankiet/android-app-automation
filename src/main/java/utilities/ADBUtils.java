package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Runtime.getRuntime;

public class ADBUtils {

    public void closeAppUsingADBCommand(String deviceUdid, String appPkgName) throws IOException, InterruptedException {
        String commandStopActivity = String.format("adb -s %s shell am force-stop " + appPkgName, deviceUdid);
        executeCommand(commandStopActivity);
    }

    public void relaunchAppUsingADBCommand(String deviceUdid, String appPkgName, String homeLaunchableActivityName) throws IOException, InterruptedException {
        String commandLaunchActivity = String.format("adb -s %s shell am start " + appPkgName + "/" + homeLaunchableActivityName, deviceUdid);
        System.out.println("**********" + commandLaunchActivity);
        executeCommand(commandLaunchActivity);
    }

    public void deleteAppiumFiles(String deviceUdid) throws IOException, InterruptedException {
        String commandForDeletingAppiumSettingsFiles = String.format("adb -s %s uninstall io.appium.settings", deviceUdid);
        String commandForDeletingAppiumUnlockFiles = String.format("adb -s %s uninstall io.appium.unlock", deviceUdid);
        executeCommand(commandForDeletingAppiumSettingsFiles);
        executeCommand(commandForDeletingAppiumUnlockFiles);
    }

    private void executeCommand(String command) throws IOException, InterruptedException {
        Process process = getRuntime().exec(command);
        process.waitFor();
    }


    public String executeAdbCommandAndReadConsoleOutput(String command) throws IOException, InterruptedException {
        Process process = getRuntime().exec(command);
        process.waitFor();

        InputStream is = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        return bufferedReader.readLine();
    }

    private void executeCommandUsingArray(String[] command) throws IOException, InterruptedException {
        for (String arg : command) {
            Process process = Runtime.getRuntime().exec(arg);
            process.waitFor();
        }
    }

    public void turnOffWifi() throws IOException, InterruptedException {
        Boolean status=checkWifiStatus();
        if (status.equals(true)) {
            String[] command = new String[]{"adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings","sleep 5","adb shell input keyevent 19","adb shell input keyevent 19","adb shell input keyevent 23","adb shell input keyevent 3"};
            executeCommandUsingArray(command);
        }
    }


    public void turnOnWifi() throws IOException, InterruptedException {

        Boolean status=checkWifiStatus();
        if (status.equals(false)) {
            String[] command = new String[]{"adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings", "sleep 15", "adb shell input keyevent 19", "adb shell input keyevent 23","adb shell input keyevent 3"};
            executeCommandUsingArray(command);
        }
    }


    public void turnOnAirplaneMode() throws IOException, InterruptedException {
        Boolean status=checkAirplaneModeStatus();

        if (status.equals(false)) {
            executeCommand("adb shell settings put global airplane_mode_on 1");
        }
    }

    public void turnOffAirplaneMode() throws IOException, InterruptedException {
        Boolean status=checkAirplaneModeStatus();

        if (status.equals(true)) {
            executeCommand("adb shell settings put global airplane_mode_on 0");
        }
    }

    public void minimizeApp() throws IOException, InterruptedException {
        executeCommand("adb shell input keyevent 3");
    }

    public  Boolean checkWifiStatus() throws IOException, InterruptedException {
        Boolean flag=false;
        String wifiStatusCommand = "adb shell dumpsys wifi | grep \"mNetworkInfo\" ";
        String[] consoleOutput = executeAdbCommandAndReadConsoleOutput(wifiStatusCommand).split(",");

        for (String output : consoleOutput) {
            if (output.equals(" state: CONNECTED/CONNECTED")) {
                flag =true;
                break;
            }
        }
        return flag;
    }


    public  Boolean checkAirplaneModeStatus() throws IOException, InterruptedException {
        Boolean flag=false;
        String airplaneModeStatus = "adb shell settings get global airplane_mode_on";
        String consoleOutput = executeAdbCommandAndReadConsoleOutput(airplaneModeStatus);

        if (consoleOutput.equals("1")) {
            flag= true;
        }
        return flag;
    }
}