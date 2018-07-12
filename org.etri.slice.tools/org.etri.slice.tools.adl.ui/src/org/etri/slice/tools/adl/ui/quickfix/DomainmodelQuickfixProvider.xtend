/*
 * generated by Xtext
 */
package org.etri.slice.tools.adl.ui.quickfix

import org.eclipse.xtext.ui.editor.quickfix.Fix
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
import org.eclipse.xtext.validation.Issue
import org.eclipse.xtext.xbase.ui.quickfix.XbaseQuickfixProvider
import org.etri.slice.tools.adl.domainmodel.Context
import org.etri.slice.tools.adl.domainmodel.Control
import org.etri.slice.tools.adl.domainmodel.DomainDeclaration
import org.etri.slice.tools.adl.domainmodel.DomainModel
import org.etri.slice.tools.adl.domainmodel.Event
import org.etri.slice.tools.adl.domainmodel.Exception
import org.etri.slice.tools.adl.validation.IssueCodes

import static extension org.eclipse.xtext.EcoreUtil2.*

/**
 * Custom quickfixes.
 *
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
class DomainmodelQuickfixProvider extends XbaseQuickfixProvider {

	@Fix(IssueCodes::INVALID_TYPE_NAME)
	def capitalizeName(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Capitalize name', 'Capitalize the name.', 'upcase.png') [
			context |
			val xtextDocument = context.xtextDocument
			val firstLetter = xtextDocument.get(issue.offset, 1)
			xtextDocument.replace(issue.offset, 1, firstLetter.toUpperCase)
		]
	}
	
	@Fix(IssueCodes::INVALID_FEATURE_NAME)
	def toLowercaseName(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Lowercase name', 'Change the name to lowercase', 'lcase.png') [
			context |
			val xtextDocument = context.xtextDocument
			val firstLetter = xtextDocument.get(issue.offset, 1)
			xtextDocument.replace(issue.offset, 1, firstLetter.toLowerCase)
		]
	}
	
	@Fix(IssueCodes::DUPLICATE_ELEMENT)
	def removeDuplicatedElement(Issue issue, IssueResolutionAcceptor acceptor)
	{
		acceptor.accept(issue, 'Remove duplicated element', 'Remove duplicated element', 'delete_obj.png') [
			element, context | {
					val container = element.eContainer
					System.out.println("container " + container)		
					
					if(container instanceof DomainModel)
					{
						(container as DomainModel).elements.remove(element)
					}
					else if(container instanceof DomainDeclaration)
					{
						(container as DomainDeclaration).elements.remove(element)	
					}
				}			
		]
	}
	
	@Fix(IssueCodes::DUPLICATE_FEATURE)
	def removeDuplicatedFeature(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Remove duplicated feature', 'Remove duplicated feature', 'delete_obj.png') [
			element, context | {
					var control = element.getContainerOfType(Control)
					
					if(null !== control)
						control?.features.remove(element)
				}			
		]
	}
	
	@Fix(IssueCodes::DUPLICATE_PROPERTY)
	def removeDuplicatedProperty(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Remove duplicated property', 'Remove duplicated property', 'delete_obj.png') [
			element, context | {
					val container = element.eContainer
					System.out.println("container " + container)		
					
					if(container instanceof Context)
					{
						(container as Context).properties.remove(element)
					}
					else if(container instanceof Event)
					{
						(container as Event).properties.remove(element)	
					}
				}			
		]
	}
	
	@Fix(IssueCodes::CONTEXT_HIERARCHY_CYCLE)
	def removeContextCycleInHierarchy(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Remove supertype', '''Remove supertype '<<issue.data.get(0)>>' ''', 'delete_obj.gif') [
			element, context | (element as Context).superType = null]
	}
	
	@Fix(IssueCodes::CONTROL_HIERARCHY_CYCLE)
	def removeControlCycleInHierarchy(Issue issue, IssueResolutionAcceptor acceptor) {
//		acceptor.accept(issue, 'Remove supertype', '''Remove supertype '<<issue.data.get(0)>>' ''', 'delete_obj.gif') [
//			element, context | (element as Control).superType = null]
	}
	
	@Fix(IssueCodes::EVENT_HIERARCHY_CYCLE)
	def removeEventCycleInHierarchy(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Remove supertype', '''Remove supertype '<<issue.data.get(0)>>' ''', 'delete_obj.gif') [
			element, context | (element as Event).superType = null]
	}
	
	@Fix(IssueCodes::EXCEPTION_HIERARCHY_CYCLE)
	def removeExceptionCycleInHierarchy(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Remove supertype', '''Remove supertype '<<issue.data.get(0)>>' ''', 'delete_obj.gif') [
			element, context | (element as Exception).superType = null]
	}
}
