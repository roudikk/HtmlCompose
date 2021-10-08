package com.example.htmlcompose.demo

val DEMO_HTML = """
<body>   
    <h5>Html Headers</h5>
    <p>The supported header sizes are: h1, h2, h3, h4, h5.</p>
    <p>Each tag is then mapped to its respective header in <a href="https://developer.android.com/jetpack/compose/text">Compose typography</a>.</p>
    
    <h5>Html Images</h5>
    <p>Images are loaded using <b>Coil</b> image library:</p>
    
    <ul>
        <li>Get the url from the <u>src</u> attribute</li>
        <li>Prepend an optional <u>baseUrl</u> if the <u>src</u> doesn't start with <b>http</b></li>
        <li>Crossfade loading the images</li>
        <li>Images are cached</li>
        <li>Before an image is loaded, its aspect ratio from <u>width</u> and <u>height</u> are used to fill the required space</li>
    </ul>
    
    <p>With width/height specified:</p>
    
    <img width="1200" height="675" src="https://www.coletiv.com/static/daf4ca2417f6a0330cdcf5b008f08ba6/6050d/android-jetpack-compose-navigation.png"/>
    
    <h5>Html Paragraphs</h5>
    <p>Each paragraph must start with the <u>p</u> tag. Paragraphs support styling for <b>bold</b>, <i>italic</i>, <u>underlined</u>, <s>Line-through</s> and <a href="https://www.google.com">links</a>.</p>
    
    <h5>Html Ordered Lists</h5>
    <p>An ordered list must specify a beginning <i>ol</i> tag and <i>li</i> for each item.</p>
    
    <ol>
        <li>This is an example ordered list, starting with this first item</li>
        <li>For accessibility the number on the left will be read out before the text</li>
        <li>Items in ordered lists support <u><i><b>styling</b></i></u> same as paragraphs</li>
    </ol>
    
    <h5>Html Unordered Lists</h5>
    <p>An unordered list must specify a beginning <i>ul</i> tag and <i>li</i> for each item.</p>
    
    <ul>
        <li>This is an example unordered list, starting with this first item</li>
        <li>Items in unordered lists support <u><i><b>styling</b></i></u> same as paragraphs</li>
        <li>The bullet in the beginning grows with text size rather than display size</li>
    </ul>
    
    <h5>Html Tables</h5>
    <p>Basic tables are supported, styling and borders are not.</p>
    
    <table>
        <tr>
            <th>Header 1</th>
            <th>Header 2</th>
            <th>Header 3</th>
        </tr>
        <tr>
            <td>Item 1,1</td>
            <td>Item 1,2</td>
            <td>Item 1,3</td>
        </tr>
        <tr>
            <td>Item 2,1</td>
            <td>Item 2,2</td>
            <td>Item 2,3</td>
        </tr>
        <tr>
            <td>Item 3,1</td>
            <td>Item 3,2</td>
            <td>Item 3,3</td>
        </tr>
    </table>
    
    <h5>Html Videos</h5>
    <video>
        <source src="https://samplelib.com/lib/preview/mp4/sample-5s.mp4" type="video/mp4">
    </video>
    
    <h5>Accessibility</h5>
    
    <p>Since everything above is rendered in compose, using <u>mergeDescendants</u> is a good way of grouping elements for talkback</p>
    
    <p></p>
    
    <p>For source code check out the <a href="https://github.com/RoudyK/HtmlCompose">Github Repository</a>.</p>
</body>
""".trimIndent()
