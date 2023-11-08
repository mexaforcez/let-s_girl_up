package event;

import Charactor.*;

public class Event {
			public static boolean checkHit(Ghost dog,Wave wave,int dogSize,int waveHeight){
							if(dog.x+dogSize>wave.x&&dog.x<wave.x) {
								if(dog.y+dogSize>=wave.y-waveHeight) {
									return true;
								}
							}
							return false;
			}
			
			public static boolean checPoint(Ghost dog,Point point,int dogSize,int pointHeight){
				if(dog.x+dogSize>point.x&&dog.x<point.x) {
					if(dog.y+dogSize>=point.y-pointHeight) {
						return true;
					}
				}
				return false;
			}
			
			public static void gameStop(Wave[] wave,Environment[] env) {

			}

}