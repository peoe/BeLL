package io;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLine;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;

import graph.Vector;
import graph.Line;

public class DXFReader {

	/**
	 * Returns all Lines from within a .dxf file.
	 * 
	 * @param filePath
	 *            the path of the .dxf file
	 * @return ArrayList of Lines
	 * @throws ParseException
	 */
	@SuppressWarnings({ "rawtypes" })
	public static ArrayList<Line> getAutocadFile(String filePath) throws ParseException {
		ArrayList<Line> vcs = new ArrayList<>();

		// parsing the file to the document
		Parser parser = ParserBuilder.createDefaultParser();
		parser.parse(filePath, DXFParser.DEFAULT_ENCODING);

		DXFDocument doc = parser.getDocument();

		// extracting all DXFLines from the file
		List lst = doc.getDXFLayer("0").getDXFEntities(DXFConstants.ENTITY_TYPE_LINE);

		// converting all DXFLines to usable Lines
		for (int index = 0; index < lst.size(); index++) {
			DXFLine dxfline = (DXFLine) lst.get(index);

			Line v = new Line(
					new Vector(round2(dxfline.getStartPoint().getX()), round2(dxfline.getStartPoint().getY())),
					new Vector(round2(dxfline.getEndPoint().getX()), round2(dxfline.getEndPoint().getY())));

			vcs.add(v);
		}

		return vcs;
	}

	/**
	 * Returns a rounded Double with a maximum of two decimals.
	 * 
	 * @param val
	 *            the Double to be rounded
	 * @return rounded Double
	 */
	public static Double round2(Double val) {
		return new BigDecimal(val.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
