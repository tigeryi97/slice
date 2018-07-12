package org.etri.slice.tools.adl.generator;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.etri.slice.tools.adl.domainmodel.AgentDeclaration;
import org.etri.slice.tools.adl.domainmodel.DomainDeclaration;
import org.etri.slice.tools.adl.generator.OutputPathUtils;

@SuppressWarnings("all")
public class DistributionGenerator implements IGenerator {
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    fsa.generateFile((OutputPathUtils.sliceDistribution + "/pom.xml"), this.compileDistributionPOM(resource));
    fsa.generateFile((OutputPathUtils.sliceDistribution + "/run_slice.bat"), this.compileRunBatch());
    fsa.generateFile((OutputPathUtils.sliceDistribution + "/run_slice.sh"), this.compileRunShell());
  }
  
  public CharSequence compileDistributionPOM(final Resource resource) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<modelVersion>4.0.0</modelVersion>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<parent>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<groupId>org.etri.slice</groupId>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<artifactId>org.etri.slice</artifactId>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<version>0.9.1</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<relativePath>../pom.xml</relativePath>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</parent>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<artifactId>org.etri.slice.distribution</artifactId>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<name>The SLICE distribution</name>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<description>org.etri.slice.distribution :: distribution</description>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<dependencies>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<artifactId>org.apache.felix.ipojo.distribution.quickstart</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>${felix.ipojo.version}</version>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<type>zip</type>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<artifactId>org.apache.felix.ipojo.handler.eventadmin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>1.8.0</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<artifactId>org.apache.felix.eventadmin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>1.4.2</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<artifactId>org.apache.felix.main</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>5.6.8</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<artifactId>org.apache.felix.configadmin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<version>1.8.4</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<artifactId>org.apache.felix.http.api</artifactId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<version>2.3.2</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<artifactId>org.apache.felix.http.jetty</artifactId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<version>3.0.2</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<artifactId>org.apache.felix.http.servlet-api</artifactId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<version>1.1.0</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<artifactId>org.apache.felix.webconsole</artifactId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<version>4.3.4-all</version>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<scope>provided</scope>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>\t\t\t\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<artifactId>org.apache.felix.webconsole.plugins.obr</artifactId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<version>1.0.4</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<artifactId>org.apache.felix.webconsole.plugins.event</artifactId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<version>1.1.8</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<artifactId>org.apache.felix.webconsole.plugins.memoryusage</artifactId>");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("<version>1.0.8</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<groupId>org.etri.slice</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<artifactId>org.etri.slice.api</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>0.9.1</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<groupId>org.etri.slice</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<artifactId>org.etri.slice.core</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>0.9.1</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<groupId>org.etri.slice</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<artifactId>org.etri.slice.commons</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>0.9.1</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>\t\t\t\t\t\t\t\t\t\t");
    _builder.newLine();
    {
      Iterable<DomainDeclaration> _filter = Iterables.<DomainDeclaration>filter(IteratorExtensions.<EObject>toIterable(resource.getAllContents()), DomainDeclaration.class);
      for(final DomainDeclaration e : _filter) {
        _builder.append("\t\t");
        _builder.append("<dependency>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("<groupId>org.etri.slice.commons</groupId>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("<artifactId>org.etri.slice.commons.");
        QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(e);
        _builder.append(_fullyQualifiedName, "\t\t\t");
        _builder.append("</artifactId>");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("<version>0.9.1</version>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("</dependency>");
        _builder.newLine();
      }
    }
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("<!--\t");
    _builder.newLine();
    {
      Iterable<AgentDeclaration> _filter_1 = Iterables.<AgentDeclaration>filter(IteratorExtensions.<EObject>toIterable(resource.getAllContents()), AgentDeclaration.class);
      for(final AgentDeclaration e_1 : _filter_1) {
        _builder.append("\t\t");
        _builder.append("<dependency>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("<groupId>org.etri.slice</groupId>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("<artifactId>org.etri.slice.agents.");
        QualifiedName _fullyQualifiedName_1 = this._iQualifiedNameProvider.getFullyQualifiedName(e_1.eContainer());
        _builder.append(_fullyQualifiedName_1, "\t\t\t");
        _builder.append(".");
        String _lowerCase = e_1.getName().toLowerCase();
        _builder.append(_lowerCase, "\t\t\t");
        _builder.append("</artifactId>");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("<version>0.9.1</version>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("</dependency>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("<dependency>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("<groupId>org.etri.slice.devices</groupId>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("<artifactId>org.etri.slice.devices.");
        QualifiedName _fullyQualifiedName_2 = this._iQualifiedNameProvider.getFullyQualifiedName(e_1.eContainer());
        _builder.append(_fullyQualifiedName_2, "\t\t\t");
        _builder.append(".");
        String _lowerCase_1 = e_1.getName().toLowerCase();
        _builder.append(_lowerCase_1, "\t\t\t");
        _builder.append("</artifactId>");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("<version>0.9.1</version>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("</dependency>\t\t\t\t\t\t\t\t\t\t");
        _builder.newLine();
      }
    }
    _builder.append("-->");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</dependencies>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<build>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<plugins>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<groupId>org.apache.maven.plugins</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<artifactId>maven-dependency-plugin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<version>3.0.1</version>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<executions>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<id>unpack-felix</id>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<phase>compile</phase>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<goal>unpack-dependencies</goal>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<includeArtifactIds>org.apache.felix.ipojo.distribution.quickstart</includeArtifactIds>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<outputDirectory>${project.build.directory}/tmp</outputDirectory>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<id>copy-felix</id>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<phase>package</phase>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<goal>copy-dependencies</goal>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<includeArtifactIds>org.apache.felix.ipojo.handler.eventadmin,org.apache.felix.eventadmin,org.apache.felix.configadmin,");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("org.apache.felix.http.jetty,org.apache.felix.http.api,org.apache.felix.http.servlet-api,org.apache.felix.webconsole,");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("org.apache.felix.webconsole.plugins.obr,org.apache.felix.webconsole.plugins.event,org.apache.felix.webconsole.plugins.memoryusage</includeArtifactIds>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<outputDirectory>${project.build.directory}/bundle</outputDirectory>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</execution>\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<id>copy-bundles</id>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<phase>package</phase>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<goal>copy-dependencies</goal>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<includeGroupIds>${project.groupId}</includeGroupIds>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<excludeArtifactIds>dependencies</excludeArtifactIds>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<outputDirectory>${project.build.directory}/bundle</outputDirectory>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</executions>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</plugin>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<artifactId>maven-resources-plugin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<version>3.0.2</version>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<executions>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<goal>copy-resources</goal>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<phase>compile</phase>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<id>copy-distribution</id>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<outputDirectory>${project.build.directory}</outputDirectory>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<resources>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t");
    _builder.append("<resource>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t\t");
    _builder.append("<directory>${project.build.directory}/tmp/ipojo-distribution-${felix.ipojo.version}</directory>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t\t");
    _builder.append("<filtering>false</filtering>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t");
    _builder.append("</resource>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t");
    _builder.append("<resource>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t\t");
    _builder.append("<directory>${project.basedir}</directory>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t\t");
    _builder.append("<includes>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t\t\t");
    _builder.append("<include>run_slice.bat</include>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t\t\t");
    _builder.append("<include>run_slice.sh</include>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t\t");
    _builder.append("</includes>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t");
    _builder.append("</resource>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("</resources>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</executions>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</plugin>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</plugins>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</build>");
    _builder.newLine();
    _builder.append("</project>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compileRunBatch() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@echo off");
    _builder.newLine();
    _builder.append("java -jar -Dcom.sun.management.jmxremote.port=3403 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false ./bin/felix.jar");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compileRunShell() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("java -jar -Dcom.sun.management.jmxremote.port=3403 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false ./bin/felix.jar");
    _builder.newLine();
    return _builder;
  }
}
