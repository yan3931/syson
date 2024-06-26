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
package org.eclipse.syson.diagram.common.view.tools;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.components.view.builder.IViewDiagramElementFinder;
import org.eclipse.sirius.components.view.builder.generated.DiagramBuilders;
import org.eclipse.sirius.components.view.builder.generated.ViewBuilders;
import org.eclipse.sirius.components.view.builder.providers.INodeToolProvider;
import org.eclipse.sirius.components.view.diagram.NodeTool;
import org.eclipse.syson.util.AQLConstants;
import org.eclipse.syson.util.IDescriptionNameGenerator;

/**
 * Node tool provider for creating both nested and owned ActionUsage inside free-form compartment.
 *
 * @author Jerome Gout
 */
public class ActionFlowCompartmentNodeToolProvider implements INodeToolProvider {

    private final IDescriptionNameGenerator nameGenerator;

    private final DiagramBuilders diagramBuilderHelper = new DiagramBuilders();

    private final ViewBuilders viewBuilderHelper = new ViewBuilders();

    private final EClass eClass;

    public ActionFlowCompartmentNodeToolProvider(EClass eClass, IDescriptionNameGenerator nameGenerator) {
        super();
        this.eClass = eClass;
        this.nameGenerator = nameGenerator;
    }

    @Override
    public NodeTool create(IViewDiagramElementFinder cache) {
        var builder = this.diagramBuilderHelper.newNodeTool();
        var nodeName = this.nameGenerator.getNodeName(this.eClass);

        var creationServiceCall = this.viewBuilderHelper.newChangeContext()
                .expression(AQLConstants.AQL_SELF + ".createSubActionUsageAndView('" + nodeName + "', 'action flow', editingContext, diagramContext, selectedNode, convertedNodes)")
                .build();

        var revealOperation = this.viewBuilderHelper.newChangeContext()
                .expression("aql:selectedNode.revealCompartment(self, diagramContext, editingContext, convertedNodes)")
                .build();

        var rootChangContext = this.viewBuilderHelper.newChangeContext()
                .expression(AQLConstants.AQL_SELF)
                .children(creationServiceCall, revealOperation)
                .build();

        return builder.name("New Action")
                .iconURLsExpression("/icons/full/obj16/ActionUsage.svg")
                .body(rootChangContext)
                .build();
    }
}
