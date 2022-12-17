
package Registers;

import Memory._20010310077_Memory;
import Utils._20010310077_Utils;

public class _20010310077_AddressRegister extends _20010310077_Registers {
    _20010310077_Utils util = new _20010310077_Utils();
    _20010310077_Memory memory = _20010310077_Memory.Instance();

    public _20010310077_AddressRegister(int size, String name) {
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

    public void connectToMemory() {
        memory.read(getBitArray());

    }

}
