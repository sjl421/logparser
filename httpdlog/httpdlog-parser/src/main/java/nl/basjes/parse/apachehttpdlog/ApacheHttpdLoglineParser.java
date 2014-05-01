/*
 * Apache HTTPD logparsing made easy
 * Copyright (C) 2013 Niels Basjes
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.basjes.parse.apachehttpdlog;

import java.io.IOException;

import nl.basjes.parse.apachehttpdlog.logformat.ApacheHttpdLogFormatDisector;
import nl.basjes.parse.core.Parser;
import nl.basjes.parse.core.exceptions.InvalidDisectorException;
import nl.basjes.parse.core.exceptions.MissingDisectorsException;
import nl.basjes.parse.http.disectors.HttpFirstLineDisector;
import nl.basjes.parse.http.disectors.QueryStringDisector;
import nl.basjes.parse.http.disectors.QueryStringFieldDisector;
import nl.basjes.parse.http.disectors.RequestCookieListDisector;
import nl.basjes.parse.http.disectors.ResponseSetCookieDisector;
import nl.basjes.parse.http.disectors.ResponseSetCookieListDisector;
import nl.basjes.parse.http.disectors.TimeStampDisector;

public class ApacheHttpdLoglineParser<RECORD> extends Parser<RECORD> {

    // --------------------------------------------

    public ApacheHttpdLoglineParser(
            final Class<RECORD> clazz,
            final String logformat) throws IOException, MissingDisectorsException, InvalidDisectorException {
        // This indicates what we need
        super(clazz);

        // The pieces we have to get there
        addDisector(new ApacheHttpdLogFormatDisector(logformat));
        addDisector(new TimeStampDisector());
        addDisector(new HttpFirstLineDisector());
        addDisector(new QueryStringDisector());
        addDisector(new QueryStringFieldDisector());
        addDisector(new RequestCookieListDisector());
        addDisector(new ResponseSetCookieListDisector());
        addDisector(new ResponseSetCookieDisector());

        // Additional pieces
        addDisectors();

        // And we define the input for this parser
        setRootType("APACHELOGLINE");
    }

    // --------------------------------------------

    /**
     * Adds some additional Disectors for disecting apache loglines
     * This can be overridden in a subclass.
     * It is recommended to do a super.addDisectors() in such cases.
     */
    public void addDisectors(){
        // To be overridden
    }

    // --------------------------------------------


}