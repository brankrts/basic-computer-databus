package Memory;

import Databus._20010310077_Databus;
import Utils._20010310077_Utils;

public class _20010310077_Memory {
    static _20010310077_Memory instance;

    _20010310077_Databus dataBus = _20010310077_Databus.Instance();

    _20010310077_Utils util = new _20010310077_Utils();
    int[] memory[];
    String name = "BELLEK";
    int[] currentState;
    int[] address;

    public static _20010310077_Memory Instance() {
        if (instance == null)
            instance = new _20010310077_Memory();
        return instance;
    }

    public _20010310077_Memory() {
        this.memory = new int[4096][16];
        setMemory();
        setCurrentState();

    }

    public void read(int[] referanceAddress) {
        System.out.println("Belleğin READ girişi aktif edildi ");
        this.currentState = this.memory[util.toDecimal(referanceAddress)];

    }

    private void write(int[] referanceAddress, int[] memoryData) {
        this.memory[util.toDecimal(referanceAddress)] = memoryData;
    }

    public int[] getCurrentState() {
        return this.currentState;
    }

    public void getCurrentDataOfBus() {
        this.currentState = dataBus.getFromDataBus(this.name);
        write(this.address, this.currentState);
    }

    public void setAddress(int address) {
        this.address = util.toBinaryArray(address, 12);
    }

    public void setAddress(int[] address) {
        this.address = address;

    }

    private void setCurrentState() {
        this.currentState = new int[16];
        util.fillArrayWithZero(this.currentState);
    }

    public void setMemory() {

        int memIndex = 0;
        for (int i = memory.length - 1; i >= 0; i--) {
            int[] bitArray = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            int tmp = i;
            int index = 0;
            while (tmp / 2 >= 1) {
                int bitMod = tmp % 2;
                tmp /= 2;
                bitArray[bitArray.length - 1 - index] = bitMod;
                index++;

            }
            bitArray[bitArray.length - 1 - index] = tmp;

            memory[memIndex] = bitArray;
            memIndex++;

        }

    }

    public String getName() {
        return this.name;
    }

}
