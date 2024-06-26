/*******************************************************************************
 * Copyright (c) 2024 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.syson.diagram.actionflow.view;

import org.eclipse.syson.util.DescriptionNameGenerator;

/**
 * Name generator used by all Action Flow View description providers.
 *
 * @author Jerome Gout
 */
public class AFVDescriptionNameGenerator extends DescriptionNameGenerator {

    public AFVDescriptionNameGenerator() {
        super("AFV");
    }
}
