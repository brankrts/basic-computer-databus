package Utils;

import Registers._20010310077_Registers;

public class _20010310077_Utils {

    public void printArray(int[] array) {

        for (int x : array) {
            System.out.print(x);
        }
    }

    public int toDecimal(int[] array) {

        int[] reverseArray = reverseArray(array);
        int key = 0;

        for (int i = 0; i < reverseArray.length; i++) {

            key += reverseArray[i] * Math.pow(2, i);

        }

        return key;
    }

    public String parseString(String[] arrayOfProces, int index) {
        String myStr = arrayOfProces[index];
        int from = myStr.indexOf("[");
        int to = myStr.indexOf("]");
        String registerName = myStr.substring(from + 1, to);
        return registerName;
    }

    public int[] toBinaryArray(int value, int arraySize) {
        int[] bitArray = new int[arraySize];
        bitArray = fillArrayWithZero(bitArray);
        if (value >= 0) {
            int index = 0;
            while (value / 2 >= 1) {
                int bitMod = value % 2;
                value /= 2;
                bitArray[bitArray.length - 1 - index] = bitMod;
                index++;
            }

            bitArray[bitArray.length - 1 - index] = value;
            return bitArray;
        }
        return bitArray;

    }

    public int[] fillArrayWithZero(int[] array) {
        int[] zeroFilledArray = array;
        for (int i = 0; i < array.length; i++) {
            zeroFilledArray[i] = 0;

        }

        return zeroFilledArray;
    }

    public int[] set16BitArray(int[] array) {
        int[] copiedArray = new int[16];

        copiedArray = fillArrayWithZero(copiedArray);
        for (int i = 0; i < array.length; i++) {
            copiedArray[copiedArray.length - array.length + i] = array[i];

        }
        return copiedArray;

    }

    public void printForAcumulator(int[] array) {
        print("DR yazacından Akümülatöre --> ");
        printArray(array);
        println(" Değeri atandı");
        print("Akümülatörün güncel değeri --> ");
        printArray(array);
        newLine();
    }

    public void printClrSignal(_20010310077_Registers currentRegister) {
        println(currentRegister.getRegisterName() + " Yazmacının CLR girişi aktif edildi");
        print(currentRegister.getRegisterName() + " Yazmacının güncel değeri -> ");
        currentRegister.setDefaultBitArray();
        printArray(currentRegister.getBitArray());
        print(" olarak ayarlandı");
        newLine();

    }

    public void printIncSignal(_20010310077_Registers currentRegister) {
        int decimal = toDecimal(currentRegister.getBitArray()) + 1;
        currentRegister.setBitArray(toBinaryArray(decimal, currentRegister.getRegisterSize()));
        print(currentRegister.getRegisterName() + " Yazmacının INC girişi aktif edildi\n");
        print(currentRegister.getRegisterName() + " Yazmacının güncel değeri -> ");
        printArray(currentRegister.getBitArray());
        newLine();

    }

    public void printLoadSignal(_20010310077_Registers currentRegister) {
        println("\n" + currentRegister.getRegisterName() + "'nin Load girişi aktif edildi ");
        print(currentRegister.getRegisterName() + "'nin güncel değeri --> ");

    }

    public int[] reverseArray(int[] array) {
        int[] reversedArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            reversedArray[array.length - 1 - i] = array[i];

        }
        return reversedArray;

    }

    public int complement(int s) {
        s = (s == 1 ? 0 : 1);
        return s;
    }

    public void printForSetDataBus(String registerName, int signal, int[] muxSelectors) {
        println(
                "X" + signal + " Sinyali aktif edildi ve " + registerName
                        + " Veri yoluna aktarım gerçekleştiriyor");
        print("Muxların seçim bitleri S2 S1 S0 [" + muxSelectors[0] + ", " + muxSelectors[1] + ", "
                + muxSelectors[2] + "] olarak ayarlandı");

    }

    public void print(String text) {
        System.out.print(text);
    }

    public void println(String text) {
        System.out.println(text);
    }

    public void newLine() {
        System.out.println("\n");
    }

    public void printForGetFromDataBus(String registerName, int signal) {
        String str = registerName.equals("BELLEK") ? "WRİTE" : "LOAD";
        print(
                registerName + " için " + str + " girişi aktif ve " + "D" + signal
                        + " sinyali aktif edildi ");

        print("\n" + registerName + " Veri yolundan --> ");

    }
}
