/*
 * @(#)ConceptList.java	1.6 06/10/30
 * 
 * Copyright (c) 2006 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

/**
 * @date   1/13/98
 * @author Jacek R. Ambroziak
 * @group  Sun Microsystems Laboratories
 */

package com.sun.java.help.search;

class ConceptList implements NonnegativeIntegerGenerator, CompressorIterator
{
  private ByteArrayDecompressor _list;
  private byte _k;
  private int  _value = 0;

  public ConceptList(byte[] array, int index)
  {
    _k = array[index];
    _list = new ByteArrayDecompressor(array, index + 1);
  }
  
  // callback
  @Override
  public void value(int val) {
    _value += val;
  }
  
  @Override
  public int first() throws Exception {
    _value = 0;
    return _list.readNext(_k, this) ? _value : END;
  }

  @Override
  public int next() throws Exception {
    return _list.readNext(_k, this) ? _value : END;
  }
}
