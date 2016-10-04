package io;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

import org.kabeja.dxf.Bounds;
import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLine;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;

import main.geometry.Line;
import main.geometry.Point;

public class DXFReader { 
	
	BufferedInputStream dxfFile;
	
	public DXFReader(String filename) {
		//File dxfFile = new File(getClass().getResourceAsStream(filename).toString());
		//dxfFile = new File()
			
		//getAutocadFile("");
	}
	
	public static ArrayList<Line> getAutocadFile(String filePath) throws ParseException {
        ArrayList<Line> lines = new ArrayList<>();
        Parser parser = ParserBuilder.createDefaultParser();
        parser.parse(filePath, DXFParser.DEFAULT_ENCODING);
        DXFDocument doc = parser.getDocument();
        System.out.println(doc.containsDXFLayer("0"));
        System.out.println(doc.getDXFLayer("0").getDXFEntityByID("0").getType());
        List<DXFLine> lst = doc.getDXFLayer("0").getDXFEntities(DXFConstants.ENTITY_TYPE_LINE);
        for (int index = 0; index < lst.size(); index++) {
            Bounds bounds = lst.get(index).getBounds();
            Line line = new Line(
                    new Point(new Double(bounds.getMinimumX()).intValue(),
                    new Double(bounds.getMinimumY()).intValue()),
                    new Point(new Double(bounds.getMaximumX()).intValue(),
                    new Double(bounds.getMaximumY()).intValue())
                    );
            lines.add(line);
        }
        return lines;
    }
	
}
