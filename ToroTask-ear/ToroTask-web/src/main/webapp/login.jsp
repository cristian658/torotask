<html>
    <head>
        <title>ToroTask</title>
        <link href="styles/examples-offline.css" rel="stylesheet">
        <link href="styles/kendo.common.min.css" rel="stylesheet">
        <link href="styles/kendo.default.min.css" rel="stylesheet">
        <link href="styles/kendo.dataviz.default.min.css" rel="stylesheet" />
        <link href="styles/kendo.dataviz.min.css" rel="stylesheet" />
        <link href="styles/kendo.rtl.min.css" rel="stylesheet">
        <script src="js/jquery.min.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/kendo.all.min.js"></script>
    </head>
    <body>
        <div id="example">
            <div class="registration">
                <form>
                    <ul>
                        <li><label for="fname">First Name:</label><input id="fname" data-bind="value: firstName" /></li>
                        <li><label for="lname">Last Name:</label><input id="lname" data-bind="value: lastName" /></li>
                        <li><label for="gender">Gender:</label><select id="gender" data-bind="source: genders, value: gender"></select></li>
                    </ul>
                    <input type="checkbox" id="agree" data-bind="checked: agreed" /> <label for="agree">I have read the licence agreement</label>
                    <br /><br />
                    <button data-bind="enabled: agreed, click: register" style="display: block">Register</button>
                </form>
            </div>
            <div class="current-state">
                <h4>Current view model state:</h4>
                <pre>
    {
        firstName: <span data-bind="text: firstName"></span>,
        lastName: <span data-bind="text: lastName"></span>,
        gender: <span data-bind="text: gender"></span>,
        agreed: <span data-bind="text: agreed"></span>
    }
                </pre>
            </div>
            <div class="confirmation" data-bind="visible: confirmed">
                Thank you for your registration, <span data-bind="text: firstName"></span> <span data-bind="text: lastName"></span>
                <br /><br />
                <button data-bind="click: startOver">Start Over</button>
            </div>
            <script>
                $(document).ready(function () {
                    var viewModel = kendo.observable({
                        firstName: "John",
                        lastName: "Doe",
                        genders: ["Male", "Female"],
                        gender: "Male",
                        agreed: false,
                        confirmed: false,
                        register: function (e) {
                            e.preventDefault();

                            this.set("confirmed", true);
                        },
                        startOver: function () {
                            this.set("confirmed", false);
                            this.set("agreed", false);
                            this.set("gender", "Male");
                            this.set("firstName", "John");
                            this.set("lastName", "Doe");
                        }
                    });

                    kendo.bind($("#example"), viewModel);
                });
            </script>

            
            

            <style scoped>
                .current-state {
                    float: left;
                    width: 200px;
                    margin: 60px 85px 0 0
                }

                .current-state pre {
                    font-size: 12px;
                }

                .registration h3 {
                    font-size: 2.5em;
                    color: #787878;
                    border-bottom: 1px solid #ccc;
                }

                .registration {
                    float: left;
                    clear: left;
                    width: 370px;
                    height: 131px;
                    margin: 30px 0 30px 30px;
                    padding: 60px 0 30px 30px;
                    background: url('../content/web/mvvm/regForm.png') transparent no-repeat 0 0;
                }

                .registration ul {
                    list-style: none;
                    margin: 0;
                    padding: 0;
                }

                .registration li {
                    height: 28px;
                    vertical-align: middle;
                    color: #000;
                }

                .registration ul label {
                    display: inline-block;
                    width: 100px;
                    text-align: right;
                    padding-right: 5px;
                    color: #000;
                }

                .registration label {
                    color: #000;
                }

                .registration ul input {
                    border: 1px solid #ddd;
                }

                .registration button {
                    float: right;
                    margin-right: 85px;
                }

                .confirmation {
                    float: left;
                    clear: left;
                    width: 274px;
                    height: 65px;
                    margin: 30px 0 30px 30px;
                    padding: 20px 30px;
                    background: url('../content/web/mvvm/confirm.png') transparent no-repeat 0 0;
                    text-align: center;
                }

                .code-details > ul {
                    list-style: none;
                    margin: 0;
                    padding: 0;
                }

                .code-details li
                {
                    height: 26px;
                    line-height: 22px;
                    vertical-align: middle;
                }

                .code-details {
                    padding: 1em;
                }
                .box {
                    clear: both;
                }
            </style>
        </div>


    </body>
</html>