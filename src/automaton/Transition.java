package automaton;

public class Transition {
	private String from;
	private String input;
	private String to;

	public Transition(String from, String input, String to) {
		this.from = from;
		this.input = input;
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "("+from+","+input+","+to+")";
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return toString().equals(o.toString());
	}
}
