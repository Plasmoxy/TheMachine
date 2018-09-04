
function randomShit() {
    return Math.random();
}

class RandomNumber extends React.Component {
    render() {
        return (<span className="text-warning">
            { randomShit() }
        </span>)
    }
}

class NumberShowcase extends React.Component {
    render() {
        return (<div>
            nummber 1 : <RandomNumber /><br/>
            nummber 2 : <RandomNumber />
        </div>);
    }
}

class Movie extends React.Component {
    render() {
        return (
            <div className="border border-danger my-2">
                <h1 className="text-danger">{this.props.title}</h1>
                <h2 className="text-secondary">{this.props.genre}</h2>
            </div>
        )
    }
}

class ChangingButton extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.onClick = this.onClick.bind(this);
    }

    onClick(e) {
        this.forceUpdate();
    }

    render() {
        return (
            <button className="btn btn-primary" onClick={this.onClick}>
                { randomShit() }
            </button>
        );
    }
}

ReactDOM.render(<div>

    <NumberShowcase />
    
    {
        (Math.random() > 0.5) ? (
            <Movie title="Frankenstein" genre="Horror" />
        ) : (
            <Movie title="Avatar" genre="Action" />
        )
    }

    <ChangingButton />
    
    </div>,
    document.getElementById("root")
);