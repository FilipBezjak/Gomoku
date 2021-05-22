package logika;

	public enum Igralec {
		O, X;
	// ce je x vrne 0, drugace pa x
	public Igralec nasprotnik() {
			return (this == X ? O : X);
		}
	public Polje getPolje() {
		return (this == X ? Polje.X : Polje.O);
}
}
