package Databus;

import Utils._20010310077_Utils;

public class _20010310077_Databus {
    static _20010310077_Databus instance;

    public static _20010310077_Databus Instance() {
        if (instance == null)
            instance = new _20010310077_Databus();
        return instance;
    }

    int busData[];
    int currentInputSignal;
    int currentOutSignal;
    int[] currentMuxSelectors;
    _20010310077_Utils utils = new _20010310077_Utils();

    public _20010310077_Databus() {
        this.busData = new int[16];
        utils.fillArrayWithZero(busData);

    }

    public void setToDataBus(int[] data, String registerName) {
        utils.printForSetDataBus(registerName, getCurrentInputSignal(), this.getMuxSelectors());
        validDataBits(data);
        utils.print(" \nVeri yolundaki değer --> ");
        utils.printArray(this.busData);
        utils.newLine();

    }

    public int[] getFromDataBus(String registerName) {
        utils.printForGetFromDataBus(registerName, getCurrentOutSignal());
        utils.printArray(busData);
        utils.print(" değerini yükledi\n");
        utils.print(registerName + " güncel değeri --> ");
        utils.printArray(busData);
        return this.busData;
    }

    public int getCurrentInputSignal() {
        return this.currentInputSignal;
    };

    public void serCurrentInputSignal(int signal) {
        this.currentInputSignal = signal;
    };

    public int getCurrentOutSignal() {
        return this.currentInputSignal;
    };

    public void setCurrentOutSignal(int signal) {
        this.currentInputSignal = signal;
    };

    public int[] getMuxSelectors() {
        return this.currentMuxSelectors;
    };

    public void setMuxSelectors(int[] muxSelectors) {
        this.currentMuxSelectors = muxSelectors;
    };

    public void clearDataBus() {
        this.busData = utils.fillArrayWithZero(this.busData);
    }

    public void validDataBits(int[] data) {
        for (int i = 0; i < data.length; i++) {
            this.busData[this.busData.length - data.length + i] = data[i];

        }

    }

}
