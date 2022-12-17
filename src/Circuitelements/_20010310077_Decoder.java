package Circuitelements;

import java.util.Map;

import Databus._20010310077_Databus;
import Memory._20010310077_Memory;
import Registers._20010310077_Registers;
import Utils._20010310077_Utils;

public class _20010310077_Decoder {
        _20010310077_Utils util = new _20010310077_Utils();
        _20010310077_Databus dataBus = _20010310077_Databus.Instance();
        int D0, D1, D2, D3, D4, D5, D6, D7, s2, s1, s0;
        _20010310077_Registers OUT;

        public void decoder(int[] muxSelects, Map<String, _20010310077_Registers> registers,
                        _20010310077_Memory memory) {
                s0 = muxSelects[2];
                s1 = muxSelects[1];
                s2 = muxSelects[0];
                D0 = util.complement(s2) & util.complement(s1) & util.complement(s0);
                D1 = util.complement(s2) & util.complement(s1) & s0;
                D2 = util.complement(s2) & s1 & util.complement(s0);
                D3 = util.complement(s2) & s1 & s0;
                D4 = s2 & util.complement(s1) & util.complement(s0);
                D5 = s2 & util.complement(s1) & s0;
                D6 = s2 & s1 & util.complement(s0);
                D7 = s2 & s1 & s0;
                if (D0 == 1)
                        setOutSignal(registers, muxSelects, "NONE");

                if (D1 == 1)
                        setOutSignal(registers, muxSelects, "AR");

                if (D2 == 1)
                        setOutSignal(registers, muxSelects, "PC");

                if (D3 == 1)
                        setOutSignal(registers, muxSelects, "DR");

                if (D4 == 1) {
                        dataBus.setCurrentOutSignal(util.toDecimal(muxSelects));
                        registers.get("DR").load();
                        registers.get("DR").toAcumulator();
                        registers.get("AC").takeToAcumulator();
                }

                if (D5 == 1)
                        setOutSignal(registers, muxSelects, "IR");

                if (D6 == 1)
                        setOutSignal(registers, muxSelects, "TR");

                if (D7 == 1) {

                        dataBus.setCurrentOutSignal(util.toDecimal(muxSelects));
                        memory.getCurrentDataOfBus();
                }

        }

        private void setOutSignal(Map<String, _20010310077_Registers> registers, int[] muxSelects,
                        String registerName) {

                dataBus.setCurrentOutSignal(util.toDecimal(muxSelects));
                registers.get(registerName).load();

        }
}
