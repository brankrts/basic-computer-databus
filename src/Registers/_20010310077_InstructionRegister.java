package Registers;

import Utils._20010310077_Utils;

public class _20010310077_InstructionRegister extends _20010310077_Registers {
    _20010310077_Utils util = new _20010310077_Utils();

    public _20010310077_InstructionRegister(int size, String name) {
        super(size, name);

    }

    @Override
    public void clear() {

        util.printClrSignal(this);

    }

    @Override
    public void inc() {

        util.printIncSignal(this);

    }

}
