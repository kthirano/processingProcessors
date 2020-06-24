public enum alu {
    ALU_AND, // = 4'b0000,
    ALU_OR,//  = 4'b0001,
    ALU_ADD,// = 4'b0010,
    ALU_SUB,// = 4'b0110,
    //ALU_LT  = 4'b0111, // Less than
    ALU_NOR,// = 4'b1100,
    ALU_XOR,// = 4'b0111,
    ALU_SLT,// = 4'b1000,
    ALU_SLTU,//= 4'b1001,
    ALU_SRA,// = 4'b0100,
    ALU_SLL,// = 4'b0011,
    ALU_SRL,// = 4'b0101,
    ALU_SGE,// = 4'b1010,
    ALU_SGEU,//= 4'b1011
}
