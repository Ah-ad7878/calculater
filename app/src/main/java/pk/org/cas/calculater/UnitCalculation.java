package pk.org.cas.calculater;

public class UnitCalculation {


    public double convertArea(String fromUnit, String toUnit, double value) {

        double meters;
        switch (fromUnit) {
            case "Acres(ac)":
                meters = value * 4046.86;
                break;
            case "Square Foot":
                meters = value * 0.092903;
                break;
            case "Square Inch":
                meters = value * 0.00064516;
                break;
            case "Square Meter":
                meters = value;
                break;
            default:
                return 0;
        }


        switch (toUnit) {
            case "Acres(ac)":
                return meters / 4046.86;
            case "Square Foot":
                return meters / 0.092903;
            case "Square Inch":
                return meters / 0.00064516;
            case "Square Meter":
                return meters;
            default:
                return 0;
        }
    }


    public double convertLength(String fromUnit, String toUnit, double value) {

        double meters;
        switch (fromUnit) {
            case "Millimeter":
                meters = value / 1000.0;
                break;
            case "Centimeter":
                meters = value / 100.0;
                break;
            case "Kilometer":
                meters = value * 1000.0;
                break;
            case "Meter":
                meters = value;
                break;
            default:
                return 0;
        }

        switch (toUnit) {
            case "Millimeter":
                return meters * 1000.0;
            case "Centimeter":
                return meters * 100.0;
            case "Kilometer":
                return meters / 1000.0;
            case "Meter":
                return meters;
            default:
                return 0;
        }
    }

    public double convertTemperature(String fromUnit, String toUnit, double value) {

        double celsius;
        switch (fromUnit) {
            case "Fahrenheit":
                celsius = (value - 32) * 5 / 9;
                break;
            case "Kelvin":
                celsius = value - 273.15;
                break;
            case "Celsius":
                celsius = value;
                break;
            default:
                return 0;
        }


        switch (toUnit) {
            case "Fahrenheit":
                return (celsius * 9 / 5) + 32;
            case "Kelvin":
                return celsius + 273.15;
            case "Celsius":
                return celsius;
            default:
                return 0;
        }
    }


    public double convertData(String fromUnit, String toUnit, double value) {

        double bytes;

        switch (fromUnit) {
            case "Byte":
                bytes = value;
                break;
            case "Kilobyte":
                bytes = value * 1024;
                break;
            case "Megabyte":
                bytes = value * 1024 * 1024;
                break;
            case "Gigabyte":
                bytes = value * 1024 * 1024 * 1024;
                break;
            case "Terabyte":
                bytes = value * 1024d * 1024 * 1024 * 1024;
                break;
            default:
                return 0;
        }


        switch (toUnit) {
            case "Byte":
                return bytes;
            case "Kilobyte":
                return bytes / 1024;
            case "Megabyte":
                return bytes / (1024 * 1024);
            case "Gigabyte":
                return bytes / (1024 * 1024 * 1024);
            case "Terabyte":
                return bytes / (1024d * 1024 * 1024 * 1024);
            default:
                return 0;
        }
    }


    public double taxCalculate(String fromUnit, String toUnit, double value) {
        double decimal;

        if (fromUnit.equals("Percentage")) {
            decimal = value / 100.0;
        } else {
            decimal = value;
        }

        if (toUnit.equals("Percentage")) {
            return decimal * 100.0;
        } else {
            return decimal;
        }
    }


    public double massCalculate(String fromUnit, String toUnit, double value) {
        double grams;
        switch (fromUnit) {
            case "Milligram":
                grams = value / 1000.0;
                break;
            case "Gram":
                grams = value;
                break;
            case "Kilogram":
                grams = value * 1000.0;
                break;
            default:
                return 0;
        }

        switch (toUnit) {
            case "Milligram":
                return grams * 1000.0;
            case "Gram":
                return grams;
            case "Kilogram":
                return grams / 1000.0;
            default:
                return 0;
        }
    }

}
