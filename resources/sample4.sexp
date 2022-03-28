;		space.open();
;
;		while (true) {
;			while (x < space.getWidth() - robi.getWidth()) {
;				x = x + 1;
;				this.moveRobi(x, y);
;			}
;			while (y < space.getHeight() - robi.getHeight()) {
;				y = y + 1;
;				this.moveRobi(x, y);
;			}
;			while (x > 0) {
;				x = x - 1;
;				this.moveRobi(x, y);
;			}
;			while (y > 0) {
;				y = y - 1;
;				this.moveRobi(x, y);
;			}
;			robi.setColor(new Color((int)(Math.random() * 0x1000000)));
;		}

(space := (Space new))
(robi := (Rect new))
(space add: robi)
(x := 0)
(y := 0)
(true 
	whileTrue: (
		(
			(x < (space width)) 
				whileTrue: ( 
					(x := ( x + 1)) (robi move x y))
				)
		(
			(y < (space height)) 
				whileTrue: ( 
					(y := ( y + 1)) (robi move x y))
				)
		(
			(x > 0) 
				whileTrue: ( 
					(x := ( x - 1)) (robi move x y))
				)
		(
			(y > 0) 
				whileTrue: ( 
					(y := ( y - 1)) (robi move x y))
				)
	)
)
