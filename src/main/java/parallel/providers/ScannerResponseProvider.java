package parallel.providers;

import parallel.providers.interfaces.IScannerResponseProvider;

import java.util.Scanner;

public class ScannerResponseProvider implements IScannerResponseProvider {
    @Override
    public int getScannerIntResponse(String message) {
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        return scan.nextInt();
    }
}
