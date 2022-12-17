package Circuitelements;

import java.util.HashMap;
import java.util.Map;

import Databus._20010310077_Databus;
import Memory._20010310077_Memory;
import Registers._20010310077_Registers;
import Utils._20010310077_Utils;

public class _20010310077_Encoder {
    _20010310077_Databus dataBus = _20010310077_Databus.Instance();
    _20010310077_Memory memory = _20010310077_Memory.Instance();
    Map<String, int[]> registerMap = new HashMap<String, int[]>();
    String[] registerNames = { "None", "AR", "PC", "DR", "AC", "IR", "TR", "BELLEK" };
    Map<String, _20010310077_Registers> registers;
    _20010310077_Registers currentRegister;

    _20010310077_Decoder decoder = new _20010310077_Decoder();
    _20010310077_Utils util = new _20010310077_Utils();
    _20010310077_Multiplaxer mux = new _20010310077_Multiplaxer();

    int D0, D1, D2, D3, D4, D5, D6, D7, s0, s1, s2;
    int B0 = 0, B1 = 0, B2 = 0, B3 = 0, B4 = 0, B5 = 0, B6 = 0, B7 = 0, B8 = 0, B9 = 0, B10 = 0, B11 = 0, B12 = 0,
            B13 = 0, B14 = 0, B15 = 0;
    int bits[];

    int[][] toEncode = {
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 1, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 1 },

    };

    public _20010310077_Encoder(Map<String, _20010310077_Registers> registers, _20010310077_Memory memory) {
        this.registers = registers;
        setHashMap();

    }

    public void Encode(String registerName) {
        int[] selectors = setMuxSelectors(this.registerMap.get(registerName.toUpperCase()));
        int[][] bits = {
                getCurrenetBits(convert2Darray(), 0),
                getCurrenetBits(convert2Darray(), 1),
                getCurrenetBits(convert2Darray(), 2),
                getCurrenetBits(convert2Darray(), 3),
                getCurrenetBits(convert2Darray(), 4),
                getCurrenetBits(convert2Darray(), 5),
                getCurrenetBits(convert2Darray(), 6),
                getCurrenetBits(convert2Darray(), 7),
                getCurrenetBits(convert2Darray(), 8),
                getCurrenetBits(convert2Darray(), 9),
                getCurrenetBits(convert2Darray(), 10),
                getCurrenetBits(convert2Darray(), 11),
                getCurrenetBits(convert2Darray(), 12),
                getCurrenetBits(convert2Darray(), 13),
                getCurrenetBits(convert2Darray(), 14),
                getCurrenetBits(convert2Darray(), 15) };

        B0 = getSelectedBit(selectors, bits[0]);
        B1 = getSelectedBit(selectors, bits[1]);
        B2 = getSelectedBit(selectors, bits[2]);
        B3 = getSelectedBit(selectors, bits[3]);
        B4 = getSelectedBit(selectors, bits[4]);
        B5 = getSelectedBit(selectors, bits[5]);
        B6 = getSelectedBit(selectors, bits[6]);
        B7 = getSelectedBit(selectors, bits[7]);
        B8 = getSelectedBit(selectors, bits[8]);
        B9 = getSelectedBit(selectors, bits[9]);
        B10 = getSelectedBit(selectors, bits[10]);
        B11 = getSelectedBit(selectors, bits[11]);
        B12 = getSelectedBit(selectors, bits[12]);
        B13 = getSelectedBit(selectors, bits[13]);
        B14 = getSelectedBit(selectors, bits[14]);
        B15 = getSelectedBit(selectors, bits[15]);
        int[] currentDataBusData = { B0, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15 };
        dataBus.setMuxSelectors(selectors);
        dataBus.serCurrentInputSignal(util.toDecimal(selectors));
        dataBus.setToDataBus(currentDataBusData, registerName);

    }

    private int[] getRegisterData(String registerName) {
        currentRegister = registers.get(registerName);
        int[] registerCurrentData = currentRegister.getBitArray();
        return registerCurrentData;
    }

    private int[] getMemoryCurrentData() {
        return memory.getCurrentState();
    }

    public int[] getCurrenetBits(int[][] registersData,
            int key) {
        registersData[1] = util.set16BitArray(registersData[1]);
        registersData[2] = util.set16BitArray(registersData[2]);
        int[] currentBits = { registersData[0][key], registersData[1][key], registersData[2][key],
                registersData[3][key], registersData[4][key], registersData[5][key], registersData[6][key],
                registersData[7][key] };
        return currentBits;

    }

    private int[][] convert2Darray() {

        int[][] convertedArray = { getRegisterData("NONE"), getRegisterData("AR"),
                getRegisterData("PC"), getRegisterData("DR"), getRegisterData("AC"),
                getRegisterData("IR"), getRegisterData("TR"), getMemoryCurrentData() };
        return convertedArray;
    }

    public int getSelectedBit(int[] selectors, int[] bit) {
        return mux.mux(selectors, bit);
    }

    public int[] setMuxSelectors(int[] toEncode) {
        int[] initialSelectors = new int[3];

        D0 = toEncode[0];
        D1 = toEncode[1];
        D2 = toEncode[2];
        D3 = toEncode[3];
        D4 = toEncode[4];
        D5 = toEncode[5];
        D6 = toEncode[6];
        D7 = toEncode[7];

        s2 = D4 | D5 | D6 | D7;
        s1 = D2 | D3 | D6 | D7;
        s0 = D1 | D3 | D5 | D7;

        initialSelectors[0] = s2;
        initialSelectors[1] = s1;
        initialSelectors[2] = s0;
        return initialSelectors;

    }

    private void setHashMap() {
        for (int i = 0; i < registerNames.length; i++) {
            registerMap.put(registerNames[i], toEncode[i]);

        }

    }

    public int[] getMuxSelectors(String registerName) {
        return setMuxSelectors(registerMap.get(registerName));

    }

    public void decode(String registerName) {
        decoder.decoder(getMuxSelectors(registerName), registers, memory);
    }
}
