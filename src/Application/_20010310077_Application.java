package Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Circuitelements._20010310077_Encoder;
import Memory._20010310077_Memory;
import Registers._20010310077_AcumulatorRegister;
import Registers._20010310077_AddressRegister;
import Registers._20010310077_DataRegister;
import Registers._20010310077_InstructionRegister;
import Registers._20010310077_ProgramCounter;
import Registers._20010310077_Registers;
import Registers._20010310077_TemporaryRegister;
import Utils._20010310077_Utils;

public class _20010310077_Application {
    _20010310077_Utils util = new _20010310077_Utils();
    _20010310077_Memory memory = _20010310077_Memory.Instance();
    Map<String, _20010310077_Registers> registers = new HashMap<String, _20010310077_Registers>();
    _20010310077_Encoder encoder = new _20010310077_Encoder(registers, memory);
    _20010310077_Registers targetRegister;
    _20010310077_Registers sourceRegister;
    int sizeOfArgs;
    String[] arrayOfProcess;

    public _20010310077_Application() {
        fillRegisters();
    }

    public void start() {
        while (true) {
            Scanner scan = new Scanner(System.in);

            util.print("\nLütfen geçerli bir micro işlem giriniz. -> ");
            String microProcess = scan.nextLine();
            verifyAndTransferData(microProcess.toUpperCase());
            util.print("Devam etmek istiyor musunuz? E/H ");
            String verify = scan.nextLine();
            if (verify.toUpperCase().equals("H")) {

                break;
            }

        }

    }

    private void verifyAndTransferData(String microProcess) {
        arrayOfProcess = microProcess.split(" ");
        targetRegister = registers.get(arrayOfProcess[0]);
        sourceRegister = registers.get(arrayOfProcess[2]);

        setSizeOfArgs(arrayOfProcess);
        if (sizeOfArgs == 3) {
            transferData(arrayOfProcess);

        } else if (sizeOfArgs == 5) {

            if (targetRegister.getRegisterName().equals(sourceRegister.getRegisterName())) {
                sourceRegister.inc();
            } else {
                sourceRegister.inc();
                encoder.Encode(sourceRegister.getRegisterName());
                encoder.decode(targetRegister.getRegisterName());

            }

        }

    }

    private void transferData(String[] arrayOfProces) {
        boolean second = arrayOfProces[2].contains("[");
        boolean first = arrayOfProces[0].contains("[");

        if (first == false & second == false) {
            if (arrayOfProces[0].equals("AC") & arrayOfProces[2].equals("DR")) {
                sourceRegister.toAcumulator();
                targetRegister.takeToAcumulator();
            } else {

                encoder.Encode(sourceRegister.getRegisterName());
                encoder.decode(targetRegister.getRegisterName());
            }

        } else if (second) {

            parseSecondString(arrayOfProces);

        } else if (first) {

            parseFirstString(arrayOfProces);

        }

        else {
            util.print("Geçersiz mikro işlem hatası!");

        }

    }

    private void parseSecondString(String[] arrayOfProces) {
        String registerName = util.parseString(arrayOfProces, 2);
        memory.read(registers.get(registerName).getBitArray());
        encoder.Encode(memory.getName());
        encoder.decode(targetRegister.getRegisterName());

    }

    public void parseFirstString(String[] arrayOfProces) {
        String registerName = util.parseString(arrayOfProces, 0);

        if (!isNumeric(registerName)) {

            memory.setAddress(registers.get(registerName).getBitArray());

        } else {
            memory.setAddress(Integer.parseInt(registerName));
        }
        encoder.Encode(sourceRegister.getRegisterName());
        encoder.decode(memory.getName());

    }

    private void setSizeOfArgs(String[] arrayOfProces) {
        this.sizeOfArgs = arrayOfProces.length;

    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void fillRegisters() {
        registers.put("NONE", new _20010310077_TemporaryRegister(16, "NONE"));
        registers.put("AR", new _20010310077_AddressRegister(12, "AR"));
        registers.put("PC", new _20010310077_ProgramCounter(12, "PC"));
        registers.put("DR", new _20010310077_DataRegister(16, "DR"));
        registers.put("AC", new _20010310077_AcumulatorRegister(16, "AC"));
        registers.put("IR", new _20010310077_InstructionRegister(16, "IR"));
        registers.put("TR", new _20010310077_TemporaryRegister(16, "TR"));

    }
}
