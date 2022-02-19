import Star from "components/Star";
import "./styles.css";

type Props = {
  score: number;
}

function getFills(score: number) {
  const fills = [0, 0, 0, 0, 0];
  const integerPart = Math.floor(score);
  for (let i = 0; i < integerPart; i++) {
    fills[i] = 1;
  }
  const diff = score - integerPart;
  if (diff > 0) {
    fills[integerPart] = 0.5;
  }
  return fills;
}

function MovieStars( { score } : Props) {

  const fills = getFills(score);

  let keyStars = 1;

  return (
    <div className="dsmovie-stars-container">
     {
       fills.map( (fill) => 
         <Star key={keyStars++} fill={fill} /> )
     }
    </div>
  );
}

export default MovieStars;
