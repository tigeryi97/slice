package org.etri.slice.tools.adl.generator;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.etri.slice.tools.adl.domainmodel.AgentDeclaration;
import org.etri.slice.tools.adl.generator.AgentGenerator;
import org.etri.slice.tools.adl.generator.DeviceGenerator;
import org.etri.slice.tools.adl.generator.DistributionGenerator;
import org.etri.slice.tools.adl.generator.DomainModelGenerator;

@SuppressWarnings("all")
public class ADLGenerator implements IGenerator {
  @Inject
  private AgentGenerator agentGenerator;
  
  @Inject
  private DeviceGenerator deviceGenerator;
  
  @Inject
  private DomainModelGenerator domainGenerator;
  
  @Inject
  private DistributionGenerator distributionGenerator;
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    this.generateMavenProject(resource, fsa);
    this.domainGenerator.doGenerate(resource, fsa);
    int _size = IterableExtensions.size(Iterables.<AgentDeclaration>filter(IteratorExtensions.<EObject>toIterable(resource.getAllContents()), AgentDeclaration.class));
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      this.agentGenerator.doGenerate(resource, fsa);
      this.deviceGenerator.doGenerate(resource, fsa);
      this.distributionGenerator.doGenerate(resource, fsa);
    }
  }
  
  public void generateMavenProject(final Resource resource, final IFileSystemAccess fsa) {
    fsa.generateFile("license-header.txt", this.compileLicenseHeader());
    fsa.generateFile("pom.xml", this.compilePOM(resource));
  }
  
  public CharSequence compileLicenseHeader() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Copyright (c) ${inceptionyear}-${year} ${holder} (${contact})");
    _builder.newLine();
    _builder.append("http://slice.etri.re.kr");
    _builder.newLine();
    _builder.newLine();
    _builder.append("This file is part of ${name}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("This Program is free software; you can redistribute it and/or modify");
    _builder.newLine();
    _builder.append("it under the terms of the GNU General Public License as published by");
    _builder.newLine();
    _builder.append("the Free Software Foundation; either version 2, or (at your option)");
    _builder.newLine();
    _builder.append("any later version.");
    _builder.newLine();
    _builder.newLine();
    _builder.append("This Program is distributed in the hope that it will be useful,");
    _builder.newLine();
    _builder.append("but WITHOUT ANY WARRANTY; without even the implied warranty of");
    _builder.newLine();
    _builder.append("MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the");
    _builder.newLine();
    _builder.append("GNU General Public License for more details.");
    _builder.newLine();
    _builder.newLine();
    _builder.append("You should have received a copy of the GNU General Public License");
    _builder.newLine();
    _builder.append("along with ${name}; see the file COPYING.  If not, see");
    _builder.newLine();
    _builder.append("<http://www.gnu.org/licenses/>.");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compilePOM(final Resource resource) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
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
    _builder.append("<groupId>org.etri.slice</groupId>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<artifactId>org.etri.slice</artifactId>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<version>0.9.1</version>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<packaging>pom</packaging>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<name>The ROOT project of SLICE components and applications</name>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<description>org.etri.slice parent</description>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<modules>");
    _builder.newLine();
    {
      int _size = IterableExtensions.size(Iterables.<AgentDeclaration>filter(IteratorExtensions.<EObject>toIterable(resource.getAllContents()), AgentDeclaration.class));
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _builder.append("\t\t");
        _builder.append("<module>org.etri.slice.agents</module>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("<module>org.etri.slice.devices</module>");
        _builder.newLine();
      }
    }
    _builder.append("\t\t");
    _builder.append("<module>org.etri.slice.models</module>");
    _builder.newLine();
    {
      int _size_1 = IterableExtensions.size(Iterables.<AgentDeclaration>filter(IteratorExtensions.<EObject>toIterable(resource.getAllContents()), AgentDeclaration.class));
      boolean _greaterThan_1 = (_size_1 > 0);
      if (_greaterThan_1) {
        _builder.append("\t\t");
        _builder.append("<module>org.etri.slice.rules</module>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("<module>org.etri.slice.distribution</module>");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("</modules>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<properties>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<felix.ipojo.version>1.12.1</felix.ipojo.version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<project.inceptionYear>2017</project.inceptionYear>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<slice.home>/Users/yhsuh/development/slice-project/git/slice/org.etri.slice.distribution</slice.home>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</properties>");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<distributionManagement>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<repository>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<id>slice-obr-hosted</id>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<url>http://129.254.88.119:8081/nexus/content/repositories/slice-obr/</url>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</repository>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</distributionManagement>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<repositories>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<repository>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<id>local-repo</id>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<url>file://${slice.home}/repository</url>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<releases>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<enabled>true</enabled>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<checksumPolicy>ignore</checksumPolicy>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<updatePolicy>always</updatePolicy>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</releases>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<snapshots>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<enabled>true</enabled>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<checksumPolicy>ignore</checksumPolicy>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<updatePolicy>always</updatePolicy>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</snapshots>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</repository>\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<repository>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<id>central</id>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<url>https://repo.maven.apache.org/maven2/</url>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</repository>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</repositories>");
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
    _builder.append("<artifactId>org.apache.felix.ipojo</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>${felix.ipojo.version}</version>");
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
    _builder.append("<artifactId>org.apache.felix.ipojo.gogo</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>${felix.ipojo.version}</version>");
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
    _builder.append("<artifactId>org.apache.felix.ipojo.annotations</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>${felix.ipojo.version}</version>");
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
    _builder.append("<groupId>com.mycila</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<artifactId>license-maven-plugin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>3.0</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</dependencies>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<build>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<pluginManagement>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<plugins>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<artifactId>maven-bundle-plugin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<remoteOBR>slice-obr-hosted</remoteOBR>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<version>3.3.0</version>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<extensions>true</extensions>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<groupId>org.apache.felix</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<artifactId>maven-ipojo-plugin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<version>1.12.1</version>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<executions>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t");
    _builder.append("<goal>ipojo-bundle</goal>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("</goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</executions>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<groupId>org.apache.maven.plugins</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<artifactId>maven-compiler-plugin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<version>3.1</version>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<fork>true</fork>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<meminitial>512m</meminitial>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<maxmem>1024m</maxmem>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<source>1.8</source>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<target>1.8</target>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<encoding>UTF-8</encoding>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<groupId>org.codehaus.mojo</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<artifactId>properties-maven-plugin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<version>1.0.0</version>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<executions>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<phase>initialize</phase>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t");
    _builder.append("<goal>read-project-properties</goal>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("</goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t");
    _builder.append("<files>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t\t");
    _builder.append("<file>bundle.properties</file>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t");
    _builder.append("</files>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("</configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</executions>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<groupId>com.mycila</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<artifactId>license-maven-plugin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<version>3.0</version>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<inherited>false</inherited>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<header>${project.basedir}/license-header.txt</header>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<aggregate>true</aggregate>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<properties>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<name>${project.name}</name>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<inceptionyear>${project.inceptionYear}</inceptionyear>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<year>2017</year>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<holder>SLICE project team</holder>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<contact>yhsuh@etri.re.kr</contact>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</properties>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<excludes>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.xml</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.properties</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.xtrg</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.xcmd</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.xrea</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.xobj</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.pom</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.png</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.jpg</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.PNG</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.options</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.txt</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<exclude>**/*.drl</exclude>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</excludes>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<includes>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<include>**/org.etri.slice.agents/**</include>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<include>**/org.etri.slice.commons/**</include>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<include>**/org.etri.slice.rules/**</include>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</includes>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<strictCheck>true</strictCheck>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<aggregate>true</aggregate>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<executions>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t");
    _builder.append("<goal>format</goal>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("</goals>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("<phase>process-sources</phase>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("</execution>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</executions>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<groupId>org.apache.maven.plugins</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<artifactId>maven-eclipse-plugin</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<version>2.10</version>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<downloadSources>true</downloadSources>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</configuration>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</plugin>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</plugins>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</pluginManagement>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<plugins>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<plugin>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<groupId>com.mycila</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<artifactId>license-maven-plugin</artifactId>");
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
}
