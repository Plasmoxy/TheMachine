public class BrainfuckToJava {
	private StringBuilder source;
	private int ident;

	public BrainfuckToJava(String code) {
		source = new StringBuilder();
		source.append("import java.util.Scanner;\n");
		source.append("public class BFConverted {\n");
		source.append("\tprivate static int ptr;\n");
		source.append("\tprivate static byte[] mem = new byte[65535];\n");
		source.append("\tprivate static Scanner in = new Scanner(System.in);\n");
		source.append("\tpublic static void main(String[] args) {\n");
		convert(code, source);
		source.append("\t}\n");
		source.append("}\n");
		System.out.println(source.toString());
	}

	private void convert(String code, StringBuilder source) {
		for(int i = 0; i < code.length(); i++) {
			for(int t = 0; t < ident; t++) source.append('\t');
			switch(code.charAt(i)) {
				case '>': source.append("\t\tptr++;\n"); break;
				case '<': source.append("\t\tptr--;\n"); break;
				case '+': source.append("\t\tmem[ptr]++;\n"); break;
				case '-': source.append("\t\tmem[ptr]--;\n"); break;
				case '.': source.append("\t\tSystem.out.print((char) mem[ptr]);\n"); break;
				case ',': source.append("\t\tmem[ptr] = (byte) in.next().charAt(0);\n"); break;
				case '[': source.append("\t\twhile(mem[ptr] != 0) {\n"); ident++; break;
				case ']': source.append("\t\t}\n"); break;
				default: continue;
			}
			if(i+1 < code.length() && code.charAt(i+1) == ']') ident--;
		}
	}

	public static void main(String[] args) {
		new BrainfuckToJava(args[0]);
	}
}