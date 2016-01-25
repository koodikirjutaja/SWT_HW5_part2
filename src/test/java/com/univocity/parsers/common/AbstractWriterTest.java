/*******************************************************************************
 * Copyright 2016 uniVocity Software Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.univocity.parsers.common;

import com.univocity.parsers.ParserTestCase;
import com.univocity.parsers.fixed.FixedWidthFieldLengths;
import com.univocity.parsers.fixed.FixedWidthWriter;
import com.univocity.parsers.fixed.FixedWidthWriterSettings;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class AbstractWriterTest extends ParserTestCase {

    @Test
    public void testWriteRowWithObjectCollection() throws IOException {
        FixedWidthWriterSettings settings = new FixedWidthWriterSettings(new FixedWidthFieldLengths(4, 4));
        settings.getFormat().setLineSeparator("\n");
        File file = File.createTempFile("test", "csv");
        FixedWidthWriter writer = new FixedWidthWriter(file, settings);

        Collection<Object> objects = new ArrayList<Object>();
        objects.add("A");
        objects.add("B");

        writer.writeRow(objects);
        writer.close();

        assertEquals(readFileContent(file), "A   B   \n");
    }

    @Test
    public void testWriteRowWithNullObjectCollection() throws IOException {
        FixedWidthWriterSettings settings = new FixedWidthWriterSettings(new FixedWidthFieldLengths(4, 4));
		settings.getFormat().setLineSeparator("\n");
        File file = File.createTempFile("test", "csv");
        FixedWidthWriter writer = new FixedWidthWriter(file, settings);

        Collection<Object> objects = null;
        writer.writeRow(objects);
        writer.close();

        assertEquals(readFileContent(file), "");
    }

    @Test
    public void testWriteStringRows() throws IOException {
        FixedWidthWriterSettings settings = new FixedWidthWriterSettings(new FixedWidthFieldLengths(4, 4));
		settings.getFormat().setLineSeparator("\n");
        settings.addFormatForLookahead("MASTER", new FixedWidthFieldLengths(3, 3, 3, 3));

        File file = File.createTempFile("test", "csv");
        FixedWidthWriter writer = new FixedWidthWriter(file, settings);

        List<List<String>> rows = new ArrayList<List<String>>();
        rows.add(Arrays.asList("A", "B"));
        rows.add(Arrays.asList("C", "D"));
        writer.writeStringRows(rows);
        writer.close();

        assertEquals(readFileContent(file), "A   B   \nC   D   \n");
    }

    @Test
    public void testWriteBufferedWriter() throws IOException {
        FixedWidthWriterSettings settings = new FixedWidthWriterSettings(new FixedWidthFieldLengths(3, 3));
		settings.getFormat().setLineSeparator("\n");
        File file = File.createTempFile("test", "csv");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        FixedWidthWriter writer = new FixedWidthWriter(bufferedWriter, settings);
        writer.writeRow("Ã", "É");
        writer.close();
        assertEquals(readFileContent(file), "Ã  É  \n");
    }
}