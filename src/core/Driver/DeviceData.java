package core.Driver;

public class DeviceData {
    private String DEFAULT_DEVICE = "Google Pixel 7";
    private final String DEFAULT_ORIENTATION = "PORTRAIT";
    private final String DEFAULT_OS_VERSION = "12.0";

    public String getMobileDeviceOrientation() {
        return System.getProperty("deviceOrientation", DEFAULT_ORIENTATION);
    }

    public String getMobileDevice() {
        return System.getProperty("mobileDevice", DEFAULT_DEVICE);
    }

    public String getMobileDeviceOsVersion() {
        return System.getProperty("osVersion", DEFAULT_OS_VERSION);
    }
}
