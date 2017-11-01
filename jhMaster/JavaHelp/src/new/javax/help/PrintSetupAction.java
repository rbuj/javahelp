/*
 * @(#)PrintSetupAction.java	1.3 06/10/30
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

package javax.help;

import com.sun.java.help.impl.JHelpPrintHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;
import javax.swing.UIManager;

/**
 *
 * @author Stepan Marek
 * @version	1.3	10/30/06
 */
public class PrintSetupAction extends AbstractHelpAction implements PropertyChangeListener, ActionListener {
    
    private static final String NAME = "PrintSetupAction";
    
    private JHelpPrintHandler handler = null;
    
    /** Creates new BackAction */
    public PrintSetupAction(Object control) {
        
        super(control, NAME);
        
        if (getControl() instanceof JHelp) {
            
            JHelp help = (JHelp)control;
            
            handler = JHelpPrintHandler.getJHelpPrintHandler(help);
            handler.addPropertyChangeListener(this);
            
	    Locale locale = null;
	    try {
		locale = help.getModel().getHelpSet().getLocale();
	    } catch (NullPointerException npe) {
		locale = Locale.getDefault();
	    }
            putValue("tooltip", HelpUtilities.getString(locale, "tooltip." + NAME));
            putValue("access", HelpUtilities.getString(locale, "access." + NAME));

        }
        
        putValue("icon", UIManager.getIcon(NAME + ".icon"));

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (handler != null) {
            handler.printSetup();
        }
    }
    
    /**
     * This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source
     *  	and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("enabled")) {
            setEnabled(((Boolean)evt.getNewValue()));
        }
    }
}
