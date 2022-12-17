package Circuitelements;

import Utils._20010310077_Utils;

public class _20010310077_Multiplaxer {
        _20010310077_Utils util = new _20010310077_Utils();
        int I0, I1, I2, I3, I4, I5, I6, I7, s2, s1, s0, OUT;

        public int mux(int[] muxSelects, int[] bits) {
                s0 = muxSelects[2];
                s1 = muxSelects[1];
                s2 = muxSelects[0];
                I0 = util.complement(s2) & util.complement(s1) & util.complement(s0);
                I1 = util.complement(s2) & util.complement(s1) & s0;
                I2 = util.complement(s2) & s1 & util.complement(s0);
                I3 = util.complement(s2) & s1 & s0;
                I4 = s2 & util.complement(s1) & util.complement(s0);
                I5 = s2 & util.complement(s1) & s0;
                I6 = s2 & s1 & util.complement(s0);
                I7 = s2 & s1 & s0;

                OUT = (I0 == 1 ? bits[0]
                                : I1 == 1 ? bits[1]
                                                : (I2 == 1 ? bits[2]
                                                                : (I3 == 1 ? bits[3]
                                                                                : (I4 == 1 ? bits[4]
                                                                                                : (I5 == 1 ? bits[5]
                                                                                                                : (I6 == 1 ? bits[6]
                                                                                                                                : (I7 == 1 ? bits[7]

                                                                                                                                                : null)))))));
                return OUT;
        }

}
