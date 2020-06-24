import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Main {
    //SINGLE CYCLE RISCV PROCESSOR

    public static void main(String[] args) throws Exception{

        //FETCHING THE INSTRUCTIONS FROM TEXT FILE
        System.getProperty("user.dir");
        File file = new File ("src/instr.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> instructions = new ArrayList<>();
        while (sc.hasNextLine()){
            String tempIns = sc.nextLine();
            if (tempIns.length() != 32){
                throw new RISCVException("Instruction not 32 bits");
            }
            String newIns = bin.flipStr(tempIns);
            instructions.add(newIns);
        }
        sc.close();

        consoleInteract reader = new consoleInteract();

        //INSTANTIATING PARTS
        registerFile rF = new registerFile();
        dataMemory dM = new dataMemory();
        boolean done = false;
        //boolean stall = false; need this for branch instructions in pipeline
        int PC;
        int PC_next = 0;
        String instr;

        //MAIN LOOP
        while (!done){
            PC = PC_next;
            instr = instructions.get(PC);
            controller cont = new controller(instr);
            String readData1 = rF.get(bin.binToInt(instrOps.rs1(instr)));
            String readData2 = rF.get(bin.binToInt(instrOps.rs2(instr)));
            String immediate = immediate_generator.imm(instr); //above 3 will always be 32 bits

            String aluop1 = readData1;
            String aluop2 = ((cont.alu_src) ? immediate : readData2);
            alu operation = alu_controller.findOp(cont.aluop, instrOps.func3(instr), instrOps.func7(instr));
            String aluResult = aluCompute.findResult(aluop1, aluop2, operation);
            boolean branch_output = aluCompute.branchOut(aluop1, aluop2, operation);

            String memResult = dM.handleMemory(bin.uBinToInt(aluResult), readData2, instrOps.func3(instr), cont.mem_write, cont.mem_read);

            String logic_jal_data = cont.jal_mode ? "0100" : memResult;
            String reg_wr_data = cont.mem2reg != "01" ? aluResult : logic_jal_data;
            boolean reg_wr_en = cont.reg_write || cont.jalr_mode;

            rF.handleWrite(instrOps.rd(instr), reg_wr_data, reg_wr_en);

            boolean PCSrc = ((branch_output && !(instrOps.func3(instr) == "001"))^(!(branch_output) && (instrOps.func3(instr) == "001"))) && cont.branch;

            int proceed = reader.read(rF, dM, cont, PC, instr, readData1, readData2, immediate, operation, aluResult);
            if (proceed == -1){done = true;}
            if (proceed == 1 ) {
                if (PCSrc){PC_next = PC + bin.binToInt(immediate) / 2;}
                else if (cont.jalr_mode){
                    String JALRindex = bin.intToBin(bin.binToInt(readData1)+ bin.binToInt(immediate));
                    JALRindex = JALRindex.substring(0,JALRindex.length()-2) + "0";
                    PC_next = bin.uBinToInt(JALRindex);
                }
                else{PC_next++;}
                if (PC_next == instructions.size()){done = true;}
            }
        }

    }

}
