<xsl:transform version='1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>

    <xsl:template match='part_name'>
        <!--3 times-->
        <part_name>
            <xsl:apply-templates />
        </part_name>
    </xsl:template>

    <xsl:template match='port_name'>
        <!--7 times-->
        <port_name>
            <xsl:apply-templates />
        </port_name>
    </xsl:template>

    <xsl:template match='peripheral'>
        <!--5 times-->
        <peripheral>
            <xsl:apply-templates />
        </peripheral>
    </xsl:template>

    <xsl:template match='part'>
        <!--3 times-->
        <part>
            <xsl:apply-templates />
        </part>
    </xsl:template>

    <xsl:template match='origin'>
        <!--5 times-->
        <origin>
            <xsl:apply-templates />
        </origin>
    </xsl:template>

    <xsl:template match='name'>
        <!--5 times-->
        <name>
            <xsl:apply-templates />
        </name>
    </xsl:template>

    <xsl:template match='port'>
        <!--7 times-->
        <port>
            <xsl:apply-templates />
        </port>
    </xsl:template>

    <xsl:template match='critical'>
        <!--5 times-->
        <critical>
            <xsl:apply-templates />
        </critical>
    </xsl:template>

    <xsl:template match='ports'>
        <!--5 times-->
        <ports>
            <xsl:apply-templates />
        </ports>
    </xsl:template>

    <xsl:template match='Device'>
        <!--1 times-->
        <Device>
            <xsl:apply-templates />
        </Device>
    </xsl:template>

    <xsl:template match='component'>
        <!--5 times-->
        <component>
            <xsl:apply-templates />
        </component>
    </xsl:template>

    <xsl:template match='parts'>
        <!--5 times-->
        <parts>
            <xsl:apply-templates />
        </parts>
    </xsl:template>

    <xsl:template match='price'>
        <!--5 times-->
        <price>
            <xsl:apply-templates />
        </price>
    </xsl:template>

    <xsl:template match='energy_consumption'>
        <!--5 times-->
        <energy_consumption>
            <xsl:apply-templates />
        </energy_consumption>
    </xsl:template>

    <xsl:template match='cooler'>
        <!--5 times-->
        <cooler>
            <xsl:apply-templates />
        </cooler>
    </xsl:template>

</xsl:transform>