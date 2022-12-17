package Alu;

import Utils._20010310077_Utils;

public class _20010310077_Alu {
    static _20010310077_Alu instance;
    _20010310077_Utils util = new _20010310077_Utils();
    int[] storedAcumulatorData;

    public static _20010310077_Alu Instance() {
        if (instance == null)
            instance = new _20010310077_Alu();
        return instance;
    }

    public void setStoredAcumulatorData(int[] acumulatorData) {
        this.storedAcumulatorData = acumulatorData;

    }

    public int[] getStoredAcumulatorData() {

        return this.storedAcumulatorData;
    }
}
