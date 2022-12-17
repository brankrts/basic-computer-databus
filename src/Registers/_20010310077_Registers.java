package Registers;

import java.util.Arrays;

import Alu._20010310077_Alu;
import Databus._20010310077_Databus;
import Utils._20010310077_Utils;

public abstract class _20010310077_Registers {
    _20010310077_Utils util = new _20010310077_Utils();
    _20010310077_Databus dataBus = _20010310077_Databus.Instance();
    _20010310077_Alu alu = _20010310077_Alu.Instance();

    private int[] bitArray;
    private int size;
    private String registerName;

    public abstract void clear();

    public abstract void inc();

    public _20010310077_Registers(int size, String name) {
        initialize(size, name);

    }

    public void toDataBus(int[] bitArray) {
        dataBus.setToDataBus(bitArray, this.registerName);
    }

    public void load() {
        int[] dataBusData = dataBus.getFromDataBus(this.registerName);

        this.bitArray = (dataBusData.length < this.bitArray.length
                ? util.set16BitArray(dataBusData)
                : Arrays.copyOfRange(dataBusData, dataBusData.length - this.bitArray.length,
                        dataBusData.length));

        System.out.println("\n");

    }

    public String getRegisterName() {
        return this.registerName;
    }

    public int getRegisterSize() {
        return this.size;
    }

    public int[] getBitArray() {
        return this.bitArray;
    }

    public void setBitArray(int[] bitArray) {
        this.bitArray = bitArray;

    }

    private void initialize(int size, String name) {
        this.registerName = name;
        this.size = size;
        this.bitArray = new int[this.size];
        this.setDefaultBitArray();

    }

    public void setDefaultBitArray() {
        this.bitArray = util.fillArrayWithZero(bitArray);
    }

    public void toAcumulator() {

        alu.setStoredAcumulatorData(this.bitArray);

    }

    public void takeToAcumulator() {

        this.bitArray = alu.getStoredAcumulatorData();
        util.printForAcumulator(this.bitArray);

    }

}
