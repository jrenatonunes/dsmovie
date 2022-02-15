import { ReactComponent as StarFull } from "assets/img/star-full.svg";
import { ReactComponent as StarHalf } from "assets/img/star-half.svg";
import { ReactComponent as StarEmpty } from "assets/img/star-empty.svg";

type Props = {
  fill: number;
}

function Star( { fill } : Props) {

  if ( fill === 1 ) {
    return <StarFull />
  }
  else if ( fill === 0 ) {
    return <StarEmpty />
  }
  else {
    return <StarHalf />
  }

}

export default Star;