package com.example.htmlcompose.html

var TEST_HTML = """
<body>   
    <h4>Html Compose Demo</h4>
    
    <p>This html parser will provide a list of data models that can be used in any way the developer wants, in this example they are used as a list of Composables inside a <a href="https://developer.android.com/jetpack/compose/lists">LazyColumn</a> where each html element is a lazy <b>item</b>.</p>

    <p>Jsoup is being used to traverse the html, then on each element found, it would be added to one of the below data models:</p>
    
    <ol>
        <li>HtmlHeader</li>
        <li>HtmlImage</li>
        <li>HtmlOrderedList</li>
        <li>HtmlUnorderedList</li>
        <li>HtmlTable</li>
        <li>HtmlVideo</li>
    </ol>
    
    <h5>Html headers</h5>
    <p>The supported header sizes are: h1, h2, h3, h4, h5.</p>
    <p>Each tag is then mapped to its respective header in <a href="https://developer.android.com/jetpack/compose/text">Compose typography</a>.</p>
    
    <h5>Html images</h5>
    <p>Images are loaded using <b>Coil</b> image library:</p>
    
    <ul>
        <li>Get the url from the <u>src</u> attribute</li>
        <li>Prepend an optional <u>baseUrl</u> if the <u>src</u> doesn't start with <b>http</b></li>
        <li>Crossfade loading the images</li>
        <li>Images are cached</li>
        <li>Before an image is loaded, its aspect ratio from <u>width</u> and <u>height</u> are used to fill the required space
    </ul>
    
    <h5>Html Ordered List</h5>
    
    
</body>
""".trimIndent()
