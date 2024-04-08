import {
  Card,
  CardActionArea,
  CardContent,
  CardMedia,
  Typography,
} from "@mui/material";
import { Link } from "react-router-dom";

const ProductCard = ({ product }) => {
  return (
    <Card
      component={Link}
      to={`product/${product.id}`}
      sx={{ maxWidth: 345, textDecoration: "none", mb: 3 }}
    >
      <CardActionArea>
        <CardMedia
          component="img"
          height="140"
          image={product.imgUrl}
          alt={product.name}
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            {product.name}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            {product.description}
          </Typography>
          <Typography>Price: {product.price}</Typography>
        </CardContent>
      </CardActionArea>
    </Card>
  );
};

export default ProductCard;
